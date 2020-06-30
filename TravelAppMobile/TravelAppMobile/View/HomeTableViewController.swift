//
//  HomeTableViewController.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 19/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import UIKit
import MapKit
import CoreLocation

class HomeTableViewController: UITableViewController {
    var sectionSelected: Int?
    var indexCellSelected: Int?
    
    let locationManager = CLLocationManager()
    let regionInMeters: Double = 10000
     
    let loadingStructure = Structure(id: -1, name: "Caricamento", place: nil, category: "caricamento", price: 0, webSite: nil,contacts: nil, description: nil, image: nil, nReviews: 0, avgPoints: 5, imageDownloaded: UIImageCodable.init(withImage: UIImage.init(named: "DownloadingImageWBlackShade.pdf")!))
    
    var structuresSection1 = [Structure]()
    var structuresSection2 = [Structure]()
    var structuresSection3 = [Structure]()
    var structuresSection4 = [Structure]()
    
    @IBOutlet weak var placeLabel: UILabel!
    @IBOutlet weak var section1CollectionView: UICollectionView!
    @IBOutlet weak var section2CollectionView: UICollectionView!
    @IBOutlet weak var section3CollectionView: UICollectionView!
    @IBOutlet weak var section4CollectionView: UICollectionView!
        
    let controller = HomeController()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        structuresSection1.append(loadingStructure)
        
        locationManager.delegate = self
        checkLocationServices()
        
        section1CollectionView.delegate = self
        section1CollectionView.dataSource = self
        
        section2CollectionView.delegate = self
        section2CollectionView.dataSource = self
        
        section3CollectionView.delegate = self
        section3CollectionView.dataSource = self
        
        section4CollectionView.delegate = self
        section4CollectionView.dataSource = self
            
        structuresSection2 = self.controller.getStructureByFilter(name: "", place: "", category: "", contacts: "", webSite: "", lowerPrice: "", upperPrice: "", avgPoints: "4 e oltre")
        structuresSection3 = self.controller.getStructureByFilter(name: "", place: "", category: "Cibo", contacts: "", webSite: "", lowerPrice: "", upperPrice: "", avgPoints: "")
        structuresSection4 = self.controller.getStructureByFilter(name: "", place: "", category: "Attivita", contacts: "", webSite: "", lowerPrice: "", upperPrice: "", avgPoints: "")
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
    
    func checkLocationServices() {
        if CLLocationManager.locationServicesEnabled() {
            locationManager.desiredAccuracy = kCLLocationAccuracyBest
            checkLocationAuthorization()
        } else {
            // Show alert letting the user know they have to turn this on.
        }
    }
    
    func checkLocationAuthorization() {
        switch CLLocationManager.authorizationStatus() {
        case .authorizedWhenInUse:
            setUserPlaceInController()
            locationManager.startUpdatingLocation()
            break
        case .denied:
            // Show alert instructing them how to turn on permissions
            break
        case .notDetermined:
            locationManager.requestWhenInUseAuthorization()
        case .restricted:
            // Show an alert letting them know what's up
            break
        case .authorizedAlways:
            break
        @unknown default:
            fatalError()
        }
    }
    
    func setUserPlaceInController() {
        if let location = locationManager.location?.coordinate {
            let newLocation = CLLocation(latitude: location.latitude, longitude: location.longitude)
            let geocoder = CLGeocoder()
            geocoder.reverseGeocodeLocation(newLocation) { (placemarks, error) in
                if let placemarks = placemarks {
                    for place in placemarks {
                        self.placeLabel.text = place.administrativeArea?.description
                        self.controller.setUserPlace(userPlace: place)
                    
                        self.structuresSection1 = self.controller.getStructureByFilter(name: "", place: self.controller.getUserPlace().locality!, category: "", contacts: "", webSite: "", lowerPrice: "", upperPrice: "", avgPoints: "")
                        
                        if(self.structuresSection1.count < 7) {
                            self.structuresSection1.append(contentsOf: self.controller.getStructureByFilter(name: "", place: self.controller.getUserPlace().administrativeArea!, category: "", contacts: "", webSite: "", lowerPrice: "", upperPrice: "", avgPoints: ""))
                            
                            if(self.structuresSection1.count < 7) {
                                 self.structuresSection1.append(contentsOf: self.controller.getStructureByFilter(name: "", place: self.controller.getUserPlace().country!, category: "", contacts: "", webSite: "", lowerPrice: "", upperPrice: "", avgPoints: ""))
                            }
                        }
                        
                        self.section1CollectionView.reloadData()
                        self.locationManager.stopUpdatingLocation()
                    }
                }
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
            cell.pointsImageView.image = UIImage (imageLiteralResourceName: GeneralReusables.starsImageAssetName(avgPoints: points))
                        
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
                            
                            cell.imageview.image = self.structuresSection1[indexPath.row].imageDownloaded?.getImage()
                        }
                    }
                } else {
                    cell.imageview.image = self.structuresSection1[indexPath.row].imageDownloaded?.getImage()
                }
            } else {
                let image = UIImage.init(named: "DefaultImageWBlackShade.pdf")
                self.structuresSection1[indexPath.row].imageDownloaded = UIImageCodable.init(withImage: image!)
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
            
            cell.pointsImageView.image = UIImage (imageLiteralResourceName: GeneralReusables.starsImageAssetName(avgPoints: points))
            
            // Manage image
            if (structuresSection2[indexPath.row].image != nil) {
                if(structuresSection2[indexPath.row].imageDownloaded == nil) {
                    cell.imageView.image = UIImage.init(named: "DownloadingImageWBlackShadeV2.pdf")
                    
                    let imageURL = URL(string: structuresSection2[indexPath.row].image!)!

                    // just not to cause a deadlock in UI!
                    DispatchQueue.global().async {
                        let imageData = try? Data(contentsOf: imageURL)

                        let image = UIImage(data: imageData!)
                        DispatchQueue.main.async {
                            self.structuresSection2[indexPath.row].imageDownloaded = UIImageCodable.init(withImage: image!)
                            
                            cell.imageView.image = self.structuresSection2[indexPath.row].imageDownloaded?.getImage()
                        }
                    }
                } else {
                    cell.imageView.image = self.structuresSection2[indexPath.row].imageDownloaded?.getImage()
                }
            } else {
                let image = UIImage.init(named: "DefaultImageWBlackShade.pdf")
                self.structuresSection2[indexPath.row].imageDownloaded = UIImageCodable.init(withImage: image!)
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
            
            cell.pointsImageView.image = UIImage (imageLiteralResourceName: GeneralReusables.starsImageAssetName(avgPoints: points))
                        
            // Manage image
            if (structuresSection3[indexPath.row].image != nil) {
                if(structuresSection3[indexPath.row].imageDownloaded == nil) {
                    cell.imageView.image = UIImage.init(named: "DownloadingImageWBlackShadeV2.pdf")
                    
                    let imageURL = URL(string: structuresSection3[indexPath.row].image!)!

                    // just not to cause a deadlock in UI!
                    DispatchQueue.global().async {
                        let imageData = try? Data(contentsOf: imageURL)

                        let image = UIImage(data: imageData!)
                        DispatchQueue.main.async {
                            self.structuresSection3[indexPath.row].imageDownloaded = UIImageCodable.init(withImage: image!)
                            
                            cell.imageView.image = self.structuresSection3[indexPath.row].imageDownloaded?.getImage()
                        }
                    }
                } else {
                    cell.imageView.image = self.structuresSection3[indexPath.row].imageDownloaded?.getImage()
                }
            } else {
                let image = UIImage.init(named: "DefaultImageWBlackShade.pdf")
                self.structuresSection3[indexPath.row].imageDownloaded = UIImageCodable.init(withImage: image!)
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

            cell.pointsImageView.image = UIImage (imageLiteralResourceName: GeneralReusables.starsImageAssetName(avgPoints: points))
            
            // Manage image
            if (structuresSection4[indexPath.row].image != nil) {
                if(structuresSection4[indexPath.row].imageDownloaded == nil) {
                    cell.imageView.image = UIImage.init(named: "DownloadingImageWBlackShadeV2.pdf")
                    
                    let imageURL = URL(string: structuresSection4[indexPath.row].image!)!

                    // just not to cause a deadlock in UI!
                    DispatchQueue.global().async {
                        let imageData = try? Data(contentsOf: imageURL)

                        let image = UIImage(data: imageData!)
                        DispatchQueue.main.async {
                            self.structuresSection4[indexPath.row].imageDownloaded = UIImageCodable.init(withImage: image!)
                            
                            cell.imageView.image = self.structuresSection4[indexPath.row].imageDownloaded?.getImage()
                        }
                    }
                } else {
                    cell.imageView.image = self.structuresSection4[indexPath.row].imageDownloaded?.getImage()
                }
            } else {
                let image = UIImage.init(named: "DefaultImageWBlackShade.pdf")
                self.structuresSection4[indexPath.row].imageDownloaded = UIImageCodable.init(withImage: image!)
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

// MARK: - Location Manager Delegate
extension HomeTableViewController: CLLocationManagerDelegate {
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        guard let location = locations.last else { return }
        let region = MKCoordinateRegion.init(center: location.coordinate, latitudinalMeters: regionInMeters, longitudinalMeters: regionInMeters)
    }
        
    func locationManager(_ manager: CLLocationManager, didChangeAuthorization status: CLAuthorizationStatus) {
        checkLocationAuthorization()
    }
}

    
    
    

