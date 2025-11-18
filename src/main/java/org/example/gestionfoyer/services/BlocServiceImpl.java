package org.example.gestionfoyer.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.example.gestionfoyer.entities.Bloc;
import org.example.gestionfoyer.entities.Chambre;
import org.example.gestionfoyer.repositories.BlocRepository;
import org.example.gestionfoyer.repositories.ChambreRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class BlocServiceImpl implements IBlocService {

    private final BlocRepository blocRepository;
    private final ChambreRepository chambreRepository;

    @Override
    public List<Bloc> retrieveBlocs() {
        log.info("Récupération de tous les blocs");
        return blocRepository.findAll();
    }

    @Override
    public Bloc updateBloc(Bloc bloc) {
        log.info("Mise à jour du bloc avec ID: {}", bloc.getIdBloc());
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc addBloc(Bloc bloc) {
        log.info("Ajout d'un nouveau bloc: {}", bloc.getNomBloc());
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc retrieveBloc(long idBloc) {
        log.info("Récupération du bloc avec ID: {}", idBloc);
        return blocRepository.findById(idBloc)
                .orElseThrow(() -> new RuntimeException("Bloc non trouvé avec l'ID: " + idBloc));
    }

    @Override
    public void removeBloc(long idBloc) {
        log.info("Suppression du bloc avec ID: {}", idBloc);
        blocRepository.deleteById(idBloc);
    }


    @Override
    public Bloc affecterChambresABloc(List<Long> chambreIds, long blocId) {
        Optional<Bloc> optionalBloc = blocRepository.findById(blocId);
        if (optionalBloc.isEmpty()) return null;

        Bloc bloc = optionalBloc.get();
        Set<Chambre> chambres = new HashSet<>();

        for (Long id : chambreIds) {
            chambreRepository.findById(id).ifPresent(chambre -> {
                chambre.setBloc(bloc);
                chambres.add(chambre);
            });
        }

        bloc.setChambres(chambres);
        return blocRepository.save(bloc);
    }


}
