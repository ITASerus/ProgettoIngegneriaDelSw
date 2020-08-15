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

    var structureList: [Structure]!
    
    var indexCellSelected: Int?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        resultsCollectionView.delegate = self
        resultsCollectionView.dataSource = self
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "SearchResultDetailStructureSegue" {
            let destinationViewController = segue.destination as! StructureDetailViewController
            
            destinationViewController.structure = structureList[indexCellSelected!]
        } else if segue.identifier == "MapViewSegue" {
            let destinationViewController = segue.destination as! MapViewController
            destinationViewController.structureList = structureList
        }
    }
    
    @IBAction func mapViewButtonPressed(_ sender: Any) {
        performSegue(withIdentifier: "MapViewSegue", sender: self)
    }
    
}

extension ResultsViewController: UICollectionViewDelegate, UICollectionViewDataSource {
   func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
       structureList.count
   }
   
   func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
    let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "LittleStructureCell", for: indexPath) as! LittleStructureCell
                   
    cell.nameLabel.text = structureList[indexPath.row].name
    cell.categoryLabel.text = structureList[indexPath.row].category == "Attivita" ? "Attività" : structureList[indexPath.row].category
    cell.priceLabel.text = structureList[indexPath.row].price?.description
    cell.nReviewsLabel.text = structureList[indexPath.row].nReviews?.description
    
    let points = structureList[indexPath.row].avgPoints ?? 0.0
    cell.pointsImageView.image = UIImage (imageLiteralResourceName: GeneralReusables.starsImageAssetName(avgPoints: points))
    
    // Manage image
    if (structureList[indexPath.row].image != nil) {
        if(structureList[indexPath.row].imageDownloaded == nil) {
            cell.imageView.image = UIImage.init(named: "DownloadingImageWBlackShadeV2.pdf")
            
            let imageURL = URL(string: structureList[indexPath.row].image!)!

            // just not to cause a deadlock in UI!
            DispatchQueue.global().async {
                let imageData = try? Data(contentsOf: imageURL)

                let image = UIImage(data: imageData!)
                DispatchQueue.main.async {
                    self.structureList[indexPath.row].imageDownloaded = UIImageCodable.init(withImage: image!)
                    
                    cell.imageView.image = self.structureList[indexPath.row].imageDownloaded?.getImage()
                }
            }
        } else {
            cell.imageView.image = self.structureList[indexPath.row].imageDownloaded?.getImage()
        }
    } else {
        let image = UIImage.init(named: "DefaultImageWBlackShade.pdf")
        self.structureList[indexPath.row].imageDownloaded = UIImageCodable.init(withImage: image!)
        cell.imageView.image = UIImage.init(named: "DefaultImageWBlackShade.pdf")
    }
    
    return cell
   }
   
   func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
          indexCellSelected = indexPath.row
          
          performSegue(withIdentifier: "SearchResultDetailStructureSegue", sender: self)
   }
}
