package org.example.gestionfoyer.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExecutionTimeAspect {

    /**
     * Part 6: AOP Aspect
     * Pointcut for the ajouterReservation method in any service class
     */
    @Pointcut("execution(* org.example.gestionfoyer.services.*Service.ajouterReservation(..))")
    public void ajouterReservationPointcut() {
    }

    /**
     * Around advice to measure execution time of ajouterReservation method
     * Logs the method name, parameters, execution time, and result
     */
    @Around("ajouterReservationPointcut()")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("========== AOP ASPECT - START ==========");

        // Get method information
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        log.info("Method: {}.{}", className, methodName);

        // Get method parameters
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            log.info("Parameters:");
            if (args.length >= 1) {
                log.info("  - Chamber ID: {}", args[0]);
            }
            if (args.length >= 2) {
                log.info("  - Student CIN: {}", args[1]);
            }
        }

        // Record start time
        long startTime = System.currentTimeMillis();
        log.info("Execution started at: {} ms", startTime);

        try {
            // Execute the actual method
            Object result = joinPoint.proceed();

            // Record end time and calculate duration
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            log.info("Execution completed successfully");
            log.info("Execution ended at: {} ms", endTime);
            log.info("⏱️  EXECUTION TIME: {} ms", executionTime);

            if (executionTime > 1000) {
                log.warn("⚠️  WARNING: Method took more than 1 second ({} ms)", executionTime);
            }

            if (result != null) {
                log.info("Result: Reservation created with ID: {}",
                        ((org.example.gestionfoyer.entities.Reservation) result).getIdReservation());
            }

            log.info("========== AOP ASPECT - END (SUCCESS) ==========");
            return result;

        } catch (Throwable e) {
            // Record error time
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            log.error("❌ Execution FAILED after {} ms", executionTime);
            log.error("Exception: {}", e.getMessage());
            log.error("Exception type: {}", e.getClass().getSimpleName());

            log.info("========== AOP ASPECT - END (FAILED) ==========");
            throw e;
        }
    }

    /**
     * Additional pointcut for all service methods (optional, for general monitoring)
     */
    @Pointcut("execution(* org.example.gestionfoyer.services.*Service.*(..))")
    public void allServiceMethodsPointcut() {
    }

    /**
     * Around advice for logging all service method calls (optional)
     * Can be uncommented for verbose logging
     */
    @Around("allServiceMethodsPointcut()")
    public Object logAllServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        // This is commented out by default to avoid excessive logging
        // Uncomment to enable verbose logging for all service methods

        /*
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        log.debug("Calling: {}.{}", className, methodName);

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        log.debug("Completed: {}.{} in {} ms", className, methodName, (endTime - startTime));

        return result;
        */

        return joinPoint.proceed();
    }
}

