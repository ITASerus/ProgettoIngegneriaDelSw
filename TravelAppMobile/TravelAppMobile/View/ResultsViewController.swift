//
//  ResultsViewController.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 12/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import UIKit

class ResultsViewController: UIViewController {

    @IBOutlet weak var resultsCollectionView: UICollectionView!

    var indexCellSelected: Int?
    var structureList = [StructureClass]()
    
    var struttura1 = StructureClass(id: 23, name: "Hotel Val D'Argento", place: "Via dei tulipani piangenti n47 Napoli", category: "Hotel", price: 350, webSite: "www.ilsitodellhotel.com", contacts: "+39 3312543463", tag: "Piscina", description: "Bell'hotel. Pieno zeppo di sedie.... effettivamente forse ci sono troppe sedie. Dovrebbero venderne qualcuna... sìsì, dovrebbero venderne un bel po'!", image: "")
       
       var struttura2 = StructureClass(id: 5, name: "Resort La Fiorita", place: "Piazza San Carlo XII Milano, 32", category: "Resort", price: 350, webSite: "www.resortdelleparlem.com", contacts: "+39 3440968543", tag: "Piscina, Accetta Cani", description: "Un residence molto lussuoso, con pomelli in avorio e lampadari fatti con lacrime di scimpanzé giovani. Le mattonelle sono state prese direttamente dal pavimento della casa di Trump e su ogni sedia è possibile notare la forma delle chiappe delle personalità illustri che hanno alloggiato qui.", image: "")
       
       var struttura3 = StructureClass(id: 12, name: "Rafting tra i Baobab", place: "Foresta Amazzonica", category: "Sport Estremo", price: 120, webSite: "www.aruba.com", contacts: "+39 0891123453", tag: "Non adatto ai bambini", description: "Hai sempre sognato di morire affogato in un tornante di un fiume in piena? Morire ammazzato da un coccodrillo o da un ippopotamo che ti scambia per la sua cena? Beh, questo è iol posto giusto! e il bello è che morirai tra il fresco scroscio dell'acqua pulitissima della foresta amazzonica. P.S Fate attenzione agli indigeni, potreste essergli indi-gesti ha ha ha", image: "")
    
    override func viewDidLoad() {
        super.viewDidLoad()

        resultsCollectionView.delegate = self
        resultsCollectionView.dataSource = self
        
        structureList.append(struttura1)
        structureList.append(struttura2)
        structureList.append(struttura3)
        structureList.append(struttura1)
        structureList.append(struttura2)
        structureList.append(struttura3)
        structureList.append(struttura1)
        structureList.append(struttura2)
        structureList.append(struttura3)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "SearchResultDetailStructureSegue" {
            let destinationViewController = segue.destination as! StructureDetailViewController
            
            destinationViewController.structure = structureList[indexCellSelected!]
        }
    }
}

extension ResultsViewController: UICollectionViewDelegate, UICollectionViewDataSource {
   func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
       return structureList.count
   }
   
   func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
    let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "LittleStructureCell", for: indexPath) as! LittleStructureCell
                   
    cell.nameLabel.text = structureList[indexPath.row].name
    cell.categoryLabel.text = structureList[indexPath.row].category
    cell.priceLabel.text = structureList[indexPath.row].price.description
    cell.nReviewsLabel.text = arc4random_uniform(9000).description
                   
    let points = arc4random_uniform(5);
                   
    switch points {
    case 0:
        cell.pointsImageView.image = UIImage (imageLiteralResourceName: "0stars.pdf")
        break
    case 1:
        cell.pointsImageView.image = UIImage (imageLiteralResourceName: "1stars.pdf")
        break
    case 2:
        cell.pointsImageView.image = UIImage (imageLiteralResourceName: "2stars.pdf")
        break
    case 3:
        cell.pointsImageView.image = UIImage (imageLiteralResourceName: "3stars.pdf")
        break
    case 4:
        cell.pointsImageView.image = UIImage (imageLiteralResourceName: "4stars.pdf")
        break
    case 5:
        cell.pointsImageView.image = UIImage (imageLiteralResourceName: "5stars.pdf")
        break
    default:
        cell.pointsImageView.image = UIImage (imageLiteralResourceName: "0stars.pdf")
        break
    }
    
    return cell
   }
   
   func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
          indexCellSelected = indexPath.row
          
          performSegue(withIdentifier: "SearchResultDetailStructureSegue", sender: self)
   }
}
