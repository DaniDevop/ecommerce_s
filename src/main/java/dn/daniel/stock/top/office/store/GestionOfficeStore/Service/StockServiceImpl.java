package dn.daniel.stock.top.office.store.GestionOfficeStore.Service;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Entity.*;
import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class StockServiceImpl implements StockService {

    private FournisseursRespository fournisseursRespository;
    private ProduitsRepository produitsRepository;
    private CategoriesRepository categoriesRepository;
    private FacturesRepository facturesRepository;
        private VentesRepository ventesRepository;
        private ClientRepository clientRepository;
        private CommandesRepository commandesRepository;

    public StockServiceImpl(FournisseursRespository fournisseursRespository,
                            ProduitsRepository produitsRepository, CategoriesRepository categoriesRepository,
                            FacturesRepository facturesRepository,
                            VentesRepository ventesRepository,ClientRepository clientRepository,CommandesRepository commandesRepository) {
        this.fournisseursRespository = fournisseursRespository;
        this.produitsRepository = produitsRepository;
        this.categoriesRepository = categoriesRepository;
        this.facturesRepository = facturesRepository;
        this.ventesRepository = ventesRepository;
        this.clientRepository=clientRepository;
        this.commandesRepository=commandesRepository;
    }

    @Override
    public Produits addProduits(Produits produits) {
        produits.setDate_creation(LocalDate.now().toString());
        return produitsRepository.save(produits);
    }

    @Override
    public Categories addCategories(Categories categories) {
        categories.setDate_creation(LocalDate.now().toString());
        return categoriesRepository.save(categories);
    }

    @Override
    public Fournisseurs addFounisseurs(Fournisseurs fournisseurs) {

        return fournisseursRespository.save(fournisseurs);
    }

    @Override
    public Factures addFactures(Factures factures) {
        factures.setDate_creation(LocalDate.now().toString());
        return facturesRepository.save(factures);
    }

    @Override
    public Ventes addVentes(Ventes ventes) {

        return ventesRepository.save(ventes);
    }

    @Override
    public Client addClient(Client client) {
        client.setDate_creation(LocalDate.now().toString());
        return clientRepository.save(client);
    }

    @Override
    public Commandes addCommandes(Commandes commandes) {
        commandes.setDate_creation(LocalDate.now().toString());
        return commandesRepository.save(commandes);
    }

    @Override
    public void addCategoriesToProduct(Integer idCategorie, Integer produit_id) {
        Optional<Categories>optionalCategories=categoriesRepository.findById(idCategorie);
        Optional<Produits>optionalProduits=produitsRepository.findById(produit_id);
        if(optionalProduits.isPresent() && optionalCategories.isPresent()){
            Categories categories=optionalCategories.get();
            Produits produits=optionalProduits.get();
            produits.setCategories(categories);
        }
    }

    @Override
    public void addFournisseursToProduits(Integer fournisseur_id, Integer produit_id) {
        Optional<Fournisseurs>optionalFournisseurs=fournisseursRespository.findById(fournisseur_id);
        Optional<Produits>optionalProduits=produitsRepository.findById(produit_id);
        if(optionalProduits.isPresent() && optionalFournisseurs.isPresent()){
            Fournisseurs fournisseurs=optionalFournisseurs.get();
            Produits produits=optionalProduits.get();
            produits.setFournisseurs(fournisseurs);
        }
    }

    @Override
    public void addCommandeToClient(Integer client_id, Integer commandes_id) {
        Optional<Client>optionalClient=clientRepository.findById(client_id);
        Optional<Commandes>optionalCommandes=commandesRepository.findById(commandes_id);

        if(optionalCommandes.isPresent() && optionalClient.isPresent()){
            Client client=optionalClient.get();
            Commandes commandes=optionalCommandes.get();
            commandes.setClient(client);
        }
        return;


    }

    @Override
    public void addFacturesToVentes(Integer id_factures, Integer qte_commande, Integer id_ventes) {

    }



    @Override
    public List<Produits> getAllProduits() {
        return produitsRepository.findAll();
    }

    @Override
    public List<Categories> getAllCategories() {
        return categoriesRepository.findAll();
    }

    @Override
    public List<Factures> getAllFactures() {
        return facturesRepository.findAll();
    }

    @Override
    public List<Ventes> getAllVentes() {
        return ventesRepository.findAll();
    }

    @Override
    public List<Fournisseurs> getAllFournisseurs() {
        return fournisseursRespository.findAll();
    }

    @Override
    public List<Client> getAllClients() {
        return null;
    }

    @Override
    public List<Commandes> getAllCommandes() {
        return null;
    }
}
