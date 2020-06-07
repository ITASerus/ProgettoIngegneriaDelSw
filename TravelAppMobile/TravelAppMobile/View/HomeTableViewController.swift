//
//  HomeTableViewController.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 19/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import UIKit

class HomeTableViewController: UITableViewController {
    var sectionSelected: Int?
    var indexCellSelected: Int?
   
    var structuresSection1 = [Structure]()
    var structuresSection2 = [Structure]()
    var structuresSection3 = [Structure]()
    var structuresSection4 = [Structure]()
    
    @IBOutlet weak var section1CollectionView: UICollectionView!
    @IBOutlet weak var section2CollectionView: UICollectionView!
    @IBOutlet weak var section3CollectionView: UICollectionView!
    @IBOutlet weak var section4CollectionView: UICollectionView!
        
    let controller = HomeController()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        section1CollectionView.delegate = self
        section1CollectionView.dataSource = self
        
        section2CollectionView.delegate = self
        section2CollectionView.dataSource = self
        
        section3CollectionView.delegate = self
        section3CollectionView.dataSource = self
        
        section4CollectionView.delegate = self
        section4CollectionView.dataSource = self

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false
    
        // Ottieni posizione utente
        //Richiami endpoint del filtro che contiene la posizione dell utente e assegni l'attay ottenuto a 'structureSection1' ad e
        //structuresSection1 = controller.getByFilter(nil, nil, nil, nil, nil, "Napoli")
        
        structuresSection1 = controller.getAllStructuresWithAvgPoints()
        structuresSection2 = controller.getAllStructuresWithAvgPoints()
        structuresSection3 = controller.getAllStructuresWithAvgPoints()
        structuresSection4 = controller.getAllStructuresWithAvgPoints()
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "StructureDetailHomeSegue" {
            let destinationViewController = segue.destination as! StructureDetailViewController
            
            if sectionSelected == 1 {
                destinationViewController.structure = structuresSection1[indexCellSelected!]
            } else if sectionSelected == 2 {
                destinationViewController.structure = structuresSection2[indexCellSelected!]
            } else if sectionSelected == 3 {
                destinationViewController.structure = structuresSection3[indexCellSelected!]
            } else {
                destinationViewController.structure = structuresSection4[indexCellSelected!]
            }
        }
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 4
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
}

// MARK: - Collection view methods

extension HomeTableViewController: UICollectionViewDelegate, UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        if collectionView == self.section1CollectionView {
            return structuresSection1.count
        } else if collectionView == self.section2CollectionView {
            return structuresSection2.count
        } else if collectionView == self.section3CollectionView {
            return structuresSection3.count
        } else {
            return structuresSection4.count
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        if collectionView == self.section1CollectionView {
            let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "BigStructureCell", for: indexPath) as! BigStructureCell
                
            cell.nameLabel.text = structuresSection1[indexPath.row].name
            cell.categoryLabel.text = structuresSection1[indexPath.row].category
            cell.priceLabel.text = structuresSection1[indexPath.row].price?.description
            cell.nReviewsLabel.text = structuresSection1[indexPath.row].nReviews?.description
            
            let points = structuresSection1[indexPath.row].avgPoints ?? 0.0

            if (points < 0.5) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "0stars.pdf")
            } else if (points < 1) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "0,5stars.pdf")
            } else if ( points < 1.5) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "1stars.pdf")
            } else if (points < 2) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "1,5stars.pdf")
            } else if (points < 2.5) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "2stars.pdf")
            } else if ( points < 3) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "2,5stars.pdf")
            } else if (points < 3.5) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "3stars.pdf")
            }else if (points < 4) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "3,5stars.pdf")
            } else if (points < 4.5) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "4stars.pdf")
            } else if (points < 5) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "4,5stars.pdf")
            } else {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "5stars.pdf")
            }
            
            // Manage image
            if (structuresSection1[indexPath.row].image != nil) {
                if(structuresSection1[indexPath.row].imageDownloaded == nil) {
                    cell.imageview.image = UIImage.init(named: "DownloadingImageWBlackShade.pdf")
                    
                    let imageURL = URL(string: structuresSection1[indexPath.row].image!)!

                    // just not to cause a deadlock in UI!
                    DispatchQueue.global().async {
                        let imageData = try? Data(contentsOf: imageURL)

                        let image = UIImage(data: imageData!)
                        DispatchQueue.main.async {
                            self.structuresSection1[indexPath.row].imageDownloaded = UIImageCodable.init(withImage: image!)
                            
                           // print(self.structuresSection1[indexPath.row].name + " image scaricata")
                            cell.imageview.image = self.structuresSection1[indexPath.row].imageDownloaded?.getImage()
                        }
                    }
                } else {
                    cell.imageview.image = self.structuresSection1[indexPath.row].imageDownloaded?.getImage()
                }
            } else {
                //print(structuresSection1[indexPath.row].name + " image nil")
                cell.imageview.image = UIImage.init(named: "DefaultImageWBlackShade.pdf")
            }
            
            return cell
            
        } else if collectionView == self.section2CollectionView {
            let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "LittleStructureCell1", for: indexPath) as! LittleStructureCell
                    
            cell.nameLabel.text = structuresSection2[indexPath.row].name
            cell.categoryLabel.text = structuresSection2[indexPath.row].category
            cell.priceLabel.text = structuresSection2[indexPath.row].price?.description
            cell.nReviewsLabel.text = structuresSection2[indexPath.row].nReviews?.description
            
            let points = structuresSection2[indexPath.row].avgPoints ?? 0.0
            
            if (points < 0.5) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "0stars.pdf")
            } else if (points < 1) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "0,5stars.pdf")
            } else if ( points < 1.5) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "1stars.pdf")
            } else if (points < 2) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "1,5stars.pdf")
            } else if (points < 2.5) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "2stars.pdf")
            } else if ( points < 3) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "2,5stars.pdf")
            } else if (points < 3.5) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "3stars.pdf")
            }else if (points < 4) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "3,5stars.pdf")
            } else if (points < 4.5) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "4stars.pdf")
            } else if (points < 5) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "4,5stars.pdf")
            } else {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "5stars.pdf")
            }
            
            // Manage image
            if (structuresSection1[indexPath.row].image != nil) {
                if(structuresSection1[indexPath.row].imageDownloaded == nil) {
                    cell.imageView.image = UIImage.init(named: "DownloadingImageWBlackShadeV2.pdf")
                    
                    let imageURL = URL(string: structuresSection1[indexPath.row].image!)!

                    // just not to cause a deadlock in UI!
                    DispatchQueue.global().async {
                        let imageData = try? Data(contentsOf: imageURL)

                        let image = UIImage(data: imageData!)
                        DispatchQueue.main.async {
                            self.structuresSection1[indexPath.row].imageDownloaded = UIImageCodable.init(withImage: image!)
                            
                           // print(self.structuresSection1[indexPath.row].name + " image scaricata")
                            cell.imageView.image = self.structuresSection1[indexPath.row].imageDownloaded?.getImage()
                        }
                    }
                } else {
                    cell.imageView.image = self.structuresSection1[indexPath.row].imageDownloaded?.getImage()
                }
            } else {
                //print(structuresSection1[indexPath.row].name + " image nil")
                cell.imageView.image = UIImage.init(named: "DefaultImageWBlackShade.pdf")
            }
            
            return cell
            
        } else if collectionView == self.section3CollectionView {
            let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "LittleStructureCell2", for: indexPath) as! LittleStructureCell
                    
            cell.nameLabel.text = structuresSection3[indexPath.row].name
            cell.categoryLabel.text = structuresSection3[indexPath.row].category
            cell.priceLabel.text = structuresSection3[indexPath.row].price?.description
            cell.nReviewsLabel.text = structuresSection3[indexPath.row].nReviews?.description
            
            let points = structuresSection3[indexPath.row].avgPoints ?? 0.0
            
            if (points < 0.5) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "0stars.pdf")
            } else if (points < 1) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "0,5stars.pdf")
            } else if ( points < 1.5) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "1stars.pdf")
            } else if (points < 2) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "1,5stars.pdf")
            } else if (points < 2.5) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "2stars.pdf")
            } else if ( points < 3) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "2,5stars.pdf")
            } else if (points < 3.5) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "3stars.pdf")
            }else if (points < 4) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "3,5stars.pdf")
            } else if (points < 4.5) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "4stars.pdf")
            } else if (points < 5) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "4,5stars.pdf")
            } else {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "5stars.pdf")
            }
            
            // Manage image
            if (structuresSection1[indexPath.row].image != nil) {
                if(structuresSection1[indexPath.row].imageDownloaded == nil) {
                    cell.imageView.image = UIImage.init(named: "DownloadingImageWBlackShadeV2.pdf")
                    
                    let imageURL = URL(string: structuresSection1[indexPath.row].image!)!

                    // just not to cause a deadlock in UI!
                    DispatchQueue.global().async {
                        let imageData = try? Data(contentsOf: imageURL)

                        let image = UIImage(data: imageData!)
                        DispatchQueue.main.async {
                            self.structuresSection1[indexPath.row].imageDownloaded = UIImageCodable.init(withImage: image!)
                            
                           // print(self.structuresSection1[indexPath.row].name + " image scaricata")
                            cell.imageView.image = self.structuresSection1[indexPath.row].imageDownloaded?.getImage()
                        }
                    }
                } else {
                    cell.imageView.image = self.structuresSection1[indexPath.row].imageDownloaded?.getImage()
                }
            } else {
                //print(structuresSection1[indexPath.row].name + " image nil")
                cell.imageView.image = UIImage.init(named: "DefaultImageWBlackShade.pdf")
            }
            
            return cell
        } else {
            let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "LittleStructureCell3", for: indexPath) as! LittleStructureCell
                    
            cell.nameLabel.text = structuresSection4[indexPath.row].name
            cell.categoryLabel.text = structuresSection4[indexPath.row].category
            cell.priceLabel.text = structuresSection4[indexPath.row].price?.description
            cell.nReviewsLabel.text = structuresSection4[indexPath.row].nReviews?.description
                
            let points = structuresSection4[indexPath.row].avgPoints ?? 0.0

            if (points < 0.5) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "0stars.pdf")
            } else if (points < 1) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "0,5stars.pdf")
            } else if ( points < 1.5) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "1stars.pdf")
            } else if (points < 2) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "1,5stars.pdf")
            } else if (points < 2.5) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "2stars.pdf")
            } else if ( points < 3) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "2,5stars.pdf")
            } else if (points < 3.5) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "3stars.pdf")
            }else if (points < 4) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "3,5stars.pdf")
            } else if (points < 4.5) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "4stars.pdf")
            } else if (points < 5) {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "4,5stars.pdf")
            } else {
                cell.pointsImageView.image = UIImage (imageLiteralResourceName: "5stars.pdf")
            }
            
            // Manage image
            if (structuresSection1[indexPath.row].image != nil) {
                if(structuresSection1[indexPath.row].imageDownloaded == nil) {
                    cell.imageView.image = UIImage.init(named: "DownloadingImageWBlackShadeV2.pdf")
                    
                    let imageURL = URL(string: structuresSection1[indexPath.row].image!)!

                    // just not to cause a deadlock in UI!
                    DispatchQueue.global().async {
                        let imageData = try? Data(contentsOf: imageURL)

                        let image = UIImage(data: imageData!)
                        DispatchQueue.main.async {
                            self.structuresSection1[indexPath.row].imageDownloaded = UIImageCodable.init(withImage: image!)
                            
                           // print(self.structuresSection1[indexPath.row].name + " image scaricata")
                            cell.imageView.image = self.structuresSection1[indexPath.row].imageDownloaded?.getImage()
                        }
                    }
                } else {
                    cell.imageView.image = self.structuresSection1[indexPath.row].imageDownloaded?.getImage()
                }
            } else {
                //print(structuresSection1[indexPath.row].name + " image nil")
                cell.imageView.image = UIImage.init(named: "DefaultImageWBlackShade.pdf")
            }
            
            return cell
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        if collectionView == section1CollectionView {
            sectionSelected = 1
        } else if collectionView == section2CollectionView {
            sectionSelected = 2
        } else if collectionView == section3CollectionView {
            sectionSelected = 3
        } else {
            sectionSelected = 4
        }
        
        indexCellSelected = indexPath.row
           
        performSegue(withIdentifier: "StructureDetailHomeSegue", sender: self)
    }
    
    
    
}
