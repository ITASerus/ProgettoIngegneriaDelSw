//
//  StructureDetailViewController.swift
//  TravelAppMobile
//
//  Created by Piero Junior Gaetani on 19/05/2020.
//  Copyright © 2020 Piero Junior Gaetani. All rights reserved.
//

import UIKit
import CoreLocation
import MapKit
import AddressBookUI

weak var mapViewPlaceOfStructure: MKMapView!
//var phoneNum : String = "" Git

class StructureDetailViewController: UIViewController {

 
    
    var recensione1 = Review(id: 9, title: "Tutto bello", description: "Camere confortevoli e pulite. Ci tornerò sicuramente!", points: 4, date: "10/01/1996", author: "Maria")
    var recensione2 = Review(id: 3, title: "Carino", description: "Nulla di eccezionale ma visto il rapporto qualità prezzo, non mi lamento.", points: 3, date: "02/10/2020", author: "Enrico")
    var recensione3 = Review(id: 10, title: "Pessimo!", description: "Non capisco come possa essere ancora aperto! Il trattamento riservatomi è ridicolo! Chiamerò la polizia e li farò chiudere baracca e baracchini!!!!", points: 0, date: "12/07/2019", author: "Genoveffa")
    
    var indexCellSelected: Int?
    var reviewsList = [Review]()
    
       @IBOutlet weak var mapViewPlaceOfStructure: MKMapView!
    @IBOutlet weak var categoryLabel: UILabel!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var placeLabel: UILabel!
    @IBOutlet weak var contactsLabel: UILabel!
    @IBOutlet weak var priceLabel: UILabel!
    @IBOutlet weak var descriptionTextView: UITextView!
    @IBOutlet weak var reviewsTableView: UITableView!
    
    var structure: Structure!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        
//        showMapOfPlace()
        
        reviewsTableView.delegate = self
        reviewsTableView.dataSource = self

        categoryLabel.text = structure.category
        nameLabel.text = structure.name
        priceLabel.text = structure.price?.description
        placeLabel.text = structure.place
        descriptionTextView.text = structure.description
        contactsLabel.text = structure.contacts
        
        reviewsList.append(recensione1)
        reviewsList.append(recensione2)
        reviewsList.append(recensione3)
        reviewsList.append(recensione2)
        reviewsList.append(recensione1)
        
        
               var placemark: CLPlacemark!
        var postoCheCerco : String = structure.place!
                
                CLGeocoder().geocodeAddressString(postoCheCerco, completionHandler: {(placemarks, error)->Void in
                    if error == nil {
                        
                        placemark = placemarks![0]
                        
                        self.mapViewPlaceOfStructure.setRegion(MKCoordinateRegion(center: CLLocationCoordinate2DMake (placemark.location!.coordinate.latitude, placemark.location!.coordinate.longitude), span: MKCoordinateSpan(latitudeDelta: 0.002, longitudeDelta: 0.002)), animated: true)
                        
                        let puntoCercato = MKPointAnnotation()
                        puntoCercato.coordinate = placemark.location!.coordinate
                        puntoCercato.title = placemark.locality
                        puntoCercato.subtitle = postoCheCerco
                        self.mapViewPlaceOfStructure.addAnnotation(puntoCercato)
                        
            }
                })
        
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "ReviewDetailSegue" {
            let destinationViewController = segue.destination as! ReviewDetailViewController
            
            destinationViewController.review = reviewsList[indexCellSelected!]
        }
    }
}

extension StructureDetailViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        reviewsList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ReviewCell", for: indexPath) as! ReviewCell
        
        cell.authorLabel.text = reviewsList[indexPath.row].author
        cell.bodyLabel.text = reviewsList[indexPath.row].description
        cell.titleLabel.text = reviewsList[indexPath.row].title
        cell.profilePictureImageView.image = UIImage(named: "photo-1500648767791-00dcc994a43e")
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        indexCellSelected = indexPath.row
        
        performSegue(withIdentifier: "ReviewDetailSegue", sender: self)
    }
}

//func callNumberOfStructure(phoneNum : String ){
//    if let url = URL(string: "tel://\(phoneNum)"), UIApplication.shared.canOpenURL(url) {
//        if #available(iOS 10, *) {
//            UIApplication.shared.open(url)
//        } else {
//            UIApplication.shared.openURL(url)
//        }
//    }
//}
/*
func showMapOfPlace (){
       
            var placemark: CLPlacemark!
            var postoCheCerco : String = "Via manzoni 75 napoli"
            
            CLGeocoder().geocodeAddressString(postoCheCerco, completionHandler: {(placemarks, error)->Void in
                if error == nil {
                    
                    placemark = placemarks![0]
                    
                  mapViewPlaceOfStructure.setRegion(MKCoordinateRegion(center: CLLocationCoordinate2DMake (placemark.location!.coordinate.latitude, placemark.location!.coordinate.longitude), span: MKCoordinateSpan(latitudeDelta: 0.002, longitudeDelta: 0.002)), animated: true)
                    
                    let puntoCercato = MKPointAnnotation()
                    puntoCercato.coordinate = placemark.location!.coordinate
                    puntoCercato.title = placemark.locality
                    puntoCercato.subtitle = postoCheCerco
                   mapViewPlaceOfStructure.addAnnotation(puntoCercato)
                    
        }
            })
    }
 */

