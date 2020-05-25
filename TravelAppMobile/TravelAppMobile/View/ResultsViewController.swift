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
    
    override func viewDidLoad() {
        super.viewDidLoad()

        resultsCollectionView.delegate = self
        resultsCollectionView.dataSource = self
        
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "SearchResultDetailStructureSegue" {
            //let destinationViewController = segue.destination as! StructureDetailViewController
            
            //destinationViewController.structure = structureList[indexCellSelected!]
        }
    }
}

extension ResultsViewController: UICollectionViewDelegate, UICollectionViewDataSource {
   func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
       return 0//structureList.count
   }
   
   func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
    let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "LittleStructureCell", for: indexPath) as! LittleStructureCell
                   
    /*cell.nameLabel.text = structureList[indexPath.row].name
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
    }*/
    
    return cell
   }
   
   func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
          indexCellSelected = indexPath.row
          
          performSegue(withIdentifier: "SearchResultDetailStructureSegue", sender: self)
   }
}
