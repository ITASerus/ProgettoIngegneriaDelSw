//
//  StructureDetailViewController.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 23/06/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import UIKit
import CoreLocation
import MapKit
import AddressBookUI

weak var mapViewPlaceOfStructure: MKMapView!


class StructureDetailViewController: UIViewController {
    
    let controller = StructureDetailController()
    
    var indexCellSelected: Int?
    var reviewsList = [Review]()
    
    @IBOutlet weak var viewBackground: UIView!
    
    @IBOutlet weak var imageOfStructure: UIImageView!
    
    @IBOutlet weak var mapViewPlaceOfStructure: MKMapView!
    @IBOutlet weak var categoryLabel: UILabel!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var placeLabel: UILabel!
    @IBOutlet weak var priceLabel: UILabel!
    @IBOutlet weak var descriptionTextView: UITextView!
    @IBOutlet weak var webSiteLabel: UILabel!
    @IBOutlet weak var reviewsTableView: UITableView!
    
    @IBOutlet weak var nReviewLabel: UILabel!
    @IBOutlet weak var pointsImageView: UIImageView!
    @IBOutlet weak var contactButton: UIButton!
    
    // Colors for review cell'sbackground
    let colorList = [UIColor(red: 1.00, green: 0.60, blue: 0.60, alpha: 1.00),
                     UIColor(red: 1.00, green: 0.82, blue: 0.53, alpha: 1.00),
                     UIColor(red: 0.68, green: 1.00, blue: 0.93, alpha: 1.00),
                     UIColor(red: 0.69, green: 0.84, blue: 1.00, alpha: 1.00),
                     UIColor(red: 0.94, green: 0.66, blue: 1.00, alpha: 1.00)]
    
    var structure: Structure!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        viewBackground.layer.cornerRadius = 20
        viewBackground.layer.shadowRadius = 5
        viewBackground.layer.shadowOpacity = 0.5
        viewBackground.layer.shadowOffset = CGSize(width: 0, height: 3)

        reviewsTableView.delegate = self
        reviewsTableView.dataSource = self

        navigationItem.title = structure.name
        nameLabel.text = structure.name
        
        categoryLabel.text = structure.category == nil ? "Non disponibile" : structure.category
        
        imageOfStructure.image = structure.imageDownloaded?.getImage()
        
        priceLabel.text = structure.price == nil ? "Non disponibile" : structure.price?.description
    
        placeLabel.text = structure.place == nil ? "Non disponibile" : structure.place
        
        descriptionTextView.text = structure.description == nil ? "Non disponibile" : structure.description
        
        webSiteLabel.text = structure.webSite == nil ? "Non disponibile" : structure.webSite
        
        contactButton.setTitle(structure.contacts == nil ? "Non disponibile" : structure.contacts, for: .normal)
        if structure.contacts == nil { contactButton.isEnabled = false }
        
        let points = structure.avgPoints ?? 0.0
        pointsImageView.image = UIImage (imageLiteralResourceName: GeneralReusables.starsImageAssetName(avgPoints: points))
        
        
        
        // Map Management
        var placemark: CLPlacemark!
        let structurePlace: String = structure.place ?? "Non disponibile"
        CLGeocoder().geocodeAddressString(structurePlace, completionHandler: {(placemarks, error)->Void in
            if error == nil {
                placemark = placemarks![0]
                            
                self.mapViewPlaceOfStructure.setRegion(MKCoordinateRegion(center: CLLocationCoordinate2DMake (placemark.location!.coordinate.latitude, placemark.location!.coordinate.longitude), span: MKCoordinateSpan(latitudeDelta: 0.002, longitudeDelta: 0.002)), animated: true)
                            
                let puntoCercato = MKPointAnnotation()
                puntoCercato.coordinate = placemark.location!.coordinate
                puntoCercato.title = placemark.locality
                puntoCercato.subtitle = structurePlace
                self.mapViewPlaceOfStructure.addAnnotation(puntoCercato)
            }
        })

    }
    
    override func viewWillAppear(_ animated: Bool) {
        if reviewsList.count < structure.nReviews! { // A new review is present so need to reload 
            reviewsList = controller.getAllReviewsWUserInfo(structureId: structure.id)
            nReviewLabel.text = structure.nReviews!.description + " recensioni"
            
            reviewsTableView.reloadData()
        }
    }
    
    @IBAction func actionButtonGoByMapToStructure(_ sender: UIButton) {
         print("posto ->\(structure.place!)")
       // goToMapOfStructure(addressPlace: structure.place!)
    }
    
    @IBAction func actionButtoCallNumberoOfStructure(_ sender: UIButton) {
            callNumberOfStructure(phoneNum: structure.contacts!)
    }
    
    @IBAction func addReviewButtonPressed(_ sender: Any) {
        if LoggedUserSingleton.shared.getLoggedUser()?.token != nil {
             performSegue(withIdentifier: "AddReviewSegue", sender: self)
        } else {
            let alertController = UIAlertController(title: "Accesso richiesto", message: "Per inserire una recensione è necessario effettuare l'accesso", preferredStyle: .alert)
            
            let goToAccess = UIAlertAction.init(title: "Vai al pannello accesso", style: .default) { (UIAlertAction) in
                self.tabBarController?.selectedIndex = 2
            }
            
            alertController.addAction(goToAccess)
            alertController.addAction(UIAlertAction(title: "Annulla", style: .cancel))
            
            self.present(alertController, animated: true, completion: nil)
        }
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "ReviewDetailSegue" {
            let destinationViewController = segue.destination as! ReviewDetailViewController
            
            destinationViewController.review = reviewsList[indexCellSelected!]
            destinationViewController.backgroundColor = colorList[indexCellSelected! % colorList.count]
        } else if segue.identifier == "AddReviewSegue" {
            let destinationViewController = segue.destination as! AddReviewViewController
            
            destinationViewController.structure = structure
        }
    }
}

extension StructureDetailViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        reviewsList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ReviewCell", for: indexPath) as! ReviewCell
        
        cell.titleLabel.text = reviewsList[indexPath.row].title
        cell.bodyLabel.text = reviewsList[indexPath.row].description
        let points = reviewsList[indexPath.row].points ?? 0.0
        cell.pointsImageView.image = UIImage (imageLiteralResourceName: GeneralReusables.starsImageAssetName(avgPoints: points))
        cell.authorLabel.text = reviewsList[indexPath.row].firstName
        
        // Date Management
        var jsonDate = reviewsList[indexPath.row].date
        let dateFormatterGet = DateFormatter()
        dateFormatterGet.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS+SSSS"
        let dateFormatterPrint = DateFormatter()
        dateFormatterPrint.dateFormat = "dd/MM/yyyy"  //"MMM d, h:mm a" for  Sep 12, 2:11 PM
        let datee = dateFormatterGet.date(from: jsonDate)
        jsonDate =  dateFormatterPrint.string(from: datee! /*?? Date()*/)
               
        cell.dateLabel.text = jsonDate
        
        cell.backgroundImageView.backgroundColor = colorList[indexPath.row % colorList.count]
        
        // Manage image
        if (reviewsList[indexPath.row].image != nil) {
            if(reviewsList[indexPath.row].imageDownloaded == nil) {
                cell.profilePictureImageView.image = UIImage.init(named: "DownloadingImageWBlackShade.pdf")
                
                let imageURL = URL(string: reviewsList[indexPath.row].image!)!

                // just not to cause a deadlock in UI!
                DispatchQueue.global().async {
                    let imageData = try? Data(contentsOf: imageURL)

                    let image = UIImage(data: imageData!)
                    DispatchQueue.main.async {
                        self.reviewsList[indexPath.row].imageDownloaded = UIImageCodable.init(withImage: image!)
                        
                        cell.profilePictureImageView.image = self.reviewsList[indexPath.row].imageDownloaded?.getImage()
                    }
                }
            } else {
                cell.profilePictureImageView.image = self.reviewsList[indexPath.row].imageDownloaded?.getImage()
            }
        } else {
            let defaultImage = UIImage.init(named: "DefaultProfile.pdf")
            self.reviewsList[indexPath.row].imageDownloaded = UIImageCodable.init(withImage: defaultImage!)
            cell.profilePictureImageView.image = defaultImage
        }
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        indexCellSelected = indexPath.row
        
        performSegue(withIdentifier: "ReviewDetailSegue", sender: self)
    }
}



private func callNumberOfStructure(phoneNum: String) {
    let phoneNumToUse = phoneNum.replacingOccurrences(of: " ", with: "")
    if let url = URL(string: "tel://+39\(phoneNumToUse)") {
        let application = UIApplication.shared
        guard application.canOpenURL(url) else {
            return
        }
        application.open(url, options: [:], completionHandler: nil)
    }
}

/*
private func goToMapOfStructure(addressPlace: String) {
        
    let source = CLGeocoder()
    geoCoder.geocodeAddressString("via manzoni 75, napoli, italy")

    MKMapItem.openMaps(with: [source, addressPlace], launchOptions: [MKLaunchOptionsDirectionsModeKey: MKLaunchOptionsDirectionsModeDriving])
    
    var addressPlaceToUse = addressPlace
     addressPlaceToUse.removeSpaces()
    
    if let url = URL(string: "map://\(addressPlaceToUse)") {
        let application = UIApplication.shared
        guard application.canOpenURL(url) else {
            return
        }
        application.open(url, options: [:], completionHandler: nil)
    }

}
*/
