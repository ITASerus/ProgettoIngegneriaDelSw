//
//  MapViewController.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 05/08/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import UIKit
import MapKit
import CoreLocation

class MapViewController: UIViewController, CLLocationManagerDelegate {

    @IBOutlet weak var mapView: MKMapView!
    let manager = CLLocationManager()
    
    var structureList: [Structure]!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        mapView.delegate = self
        
        manager.desiredAccuracy = kCLLocationAccuracyNearestTenMeters
        manager.delegate = self
        manager.requestWhenInUseAuthorization()
        manager.startUpdatingLocation()
    }
    
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        if let location = locations.first {
            manager.stopUpdatingLocation()

            render(location)
            addPins()
        }
    }
    
    func render(_ location: CLLocation) {
        let coordinate = CLLocationCoordinate2D(latitude: location.coordinate.latitude, longitude: location.coordinate.longitude)
        let span = MKCoordinateSpan(latitudeDelta: 0.1, longitudeDelta: 0.1)
        let region = MKCoordinateRegion(center: coordinate, span: span)
        
        mapView.setRegion(region, animated: true)
    }
    
    func addPins() {
        for structure in structureList {
            var placemark: CLPlacemark!
            
            let structurePlace: String = structure.place ?? "Non disponibile"
            CLGeocoder().geocodeAddressString(structurePlace, completionHandler: {(placemarks, error)->Void in
                if error == nil {
                    placemark = placemarks![0]
                    let structurePoint = MKPointAnnotation()
                    structurePoint.coordinate = placemark.location!.coordinate
                    structurePoint.title = structure.name
                    structurePoint.subtitle = structure.category
                    self.mapView.addAnnotation(structurePoint)
                }
            })
        }
    }
}

extension MapViewController: MKMapViewDelegate {
   func mapView(_ mapView: MKMapView, viewFor annotation: MKAnnotation) -> MKAnnotationView? {
        var annotationView = mapView.dequeueReusableAnnotationView(withIdentifier: "AnnotationView")

        if annotationView == nil {
            annotationView = MKAnnotationView(annotation: annotation, reuseIdentifier: "AnnotationView")
        }
        
    if annotation.subtitle == "Cibo" {
         annotationView?.image = UIImage(named:"Food Pin")
    } else if annotation.subtitle == "Attivita" {
         annotationView?.image = UIImage(named:"Exursion Pin")
    } else if annotation.subtitle == "Hotel" {
     annotationView?.image = UIImage(named:"Hotel Pin")
    } else if annotation.subtitle == "Resort" {
     annotationView?.image = UIImage(named:"Resort Pin")
    } else {
     annotationView?.image = UIImage(named:"Default Pin")
    }
       
    annotationView?.canShowCallout = true

        return annotationView
    }
    
    func mapView(_ mapView: MKMapView, didSelect view: MKAnnotationView) {
        print("Selected")
    }
}

