//
//  SearchFilterViewController.swift
//  TravelAppMobile
//
//  Created by Piero Junior Gaetani on 30/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import UIKit

class SearchFilterViewController: UIViewController {

 private let categoryArray = ["Hotel", "Resort","Experience"]
    
    //Outlet of stars
    @IBOutlet weak var star1: UIButton!
    @IBOutlet weak var star2: UIButton!
    @IBOutlet weak var star3: UIButton!
    @IBOutlet weak var star4: UIButton!
    @IBOutlet weak var star5: UIButton!

    var starButtonsArray : [UIButton] = []
    var totalStars : Int = 0
    var stringCategoryFromPicker : String = "Hotel"


    
    //PickerOutlet
    @IBOutlet weak var categoryPickerView: UIPickerView!
    
    
    //TextFieldOutlet
    @IBOutlet weak var nameTextFieldOutlet: UITextField!
    @IBOutlet weak var placesTextFieldOutlet: UITextField!
    @IBOutlet weak var webSiteTextFieldOutlet: UITextField!
    @IBOutlet weak var lowerPriceTextFieldOutlet: UITextField!
    @IBOutlet weak var upperPriceTextFieldOutlet: UITextField!

    @IBOutlet weak var contactTextFieldOutlet: UITextField!
    
    var stringBasicOfEndpoint : String = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/search/"

   
    


    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        starButtonsArray = [star1, star2, star3, star4, star5]
        
        categoryPickerView.dataSource = self
        categoryPickerView.delegate = self

         self.hideKeyboardWhenTappedAround()
        
       
       
        
        // Do any additional setup after loading the view.
    }
    
    //Set value of stars
    @IBAction func starButtonAction(_ sender: UIButton) {
        for (i, button) in starButtonsArray.enumerated() {
            if i < sender.tag {
                button.setImage(UIImage(named: "Star.png"), for: .normal)
            } else {
                button.setImage(UIImage(named: "StarGrey.png"), for: .normal)
            }
        }
        totalStars = sender.tag
    }

  
    @IBAction func printEndpointGetFinal(_ sender: UIButton) {
        
        var stringName : String = "name="
        var stringPlace : String = "&place="
        var stringCategory : String = "&category="
        var stringContacts : String = "&contacts="
        var stringWebSite : String = "&webSite="
        var stringLowerPrice : String = "&lowerPrice="
        var stringUpperPrice : String = "&upperPrice="
        var stringAvgPoints : String = "&avgPoints="

        if (nameTextFieldOutlet.text!.count != 0) {
        stringName = stringName + nameTextFieldOutlet.text!
            stringName.changeCharachtersOfSpaces()
        }else{
             stringName = stringName + "null"
        }
        
        if (placesTextFieldOutlet.text!.count != 0) {
        stringPlace = stringPlace + placesTextFieldOutlet.text!
        stringPlace.changeCharachtersOfSpaces()
        }else{
             stringPlace = stringPlace + "null"
        }

        if (webSiteTextFieldOutlet.text!.count != 0) {
            stringWebSite = stringWebSite +
                webSiteTextFieldOutlet.text!
            stringWebSite.changeCharachtersOfSpaces()
        }else{
            stringWebSite = stringWebSite + "null"
            }
        
        stringCategory = stringCategory + stringCategoryFromPicker
        
        if (contactTextFieldOutlet.text!.count != 0) {
            stringContacts = stringContacts + contactTextFieldOutlet.text!
            }else{
                 stringContacts = stringContacts + "null"
            }
        if (totalStars > 0 && totalStars < 5){
            stringAvgPoints = stringAvgPoints + String(totalStars) + "%20e%20oltre"
        }
        if  (totalStars == 5){
            stringAvgPoints = stringAvgPoints + String(totalStars)
        }
        if  (totalStars == 0){
            stringAvgPoints = stringAvgPoints + "null"
        }
        
        if (lowerPriceTextFieldOutlet.text! != "0") {
                 stringLowerPrice = stringLowerPrice + lowerPriceTextFieldOutlet.text!
                   }else{
                           stringLowerPrice = stringLowerPrice + "-1"
                   }
        
        if (upperPriceTextFieldOutlet.text! != "0") {
            stringUpperPrice = stringUpperPrice + upperPriceTextFieldOutlet.text!
            }else{
                    stringUpperPrice = stringUpperPrice + "-1"
            }
        
        

               var conclusion : String = stringBasicOfEndpoint + stringName + stringPlace + stringCategory + stringContacts + stringWebSite + stringLowerPrice + stringUpperPrice + stringAvgPoints
               
         print ("\n\n-> \n\(conclusion)\n<-\n\n")
        
        singleton.shared.endpointGetToSearch = conclusion
        
        performSegue(withIdentifier: "WebViewSegue", sender: nil)
        
        
    }
    
    
}



extension String {
    mutating func changeCharachtersOfSpaces() {
        var stringCopy = ""
        for char in self {
            if char == " " {
                stringCopy.append("%20")
            } else {
                stringCopy.append(char)
            }
        }
        self = stringCopy
    }
    
    mutating func changeCharachtersOfSpaces2() -> String{
        var stringCopy = ""
        for char in self {
            if char == " " {
                stringCopy.append("%20")
            } else {
                stringCopy.append(char)
            }
        }
        return stringCopy
    }
}

extension SearchFilterViewController: UIPickerViewDelegate, UIPickerViewDataSource {
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return categoryArray.count
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
       print ("balbalbla -> [ \(categoryArray[row]) ]")
        stringCategoryFromPicker = categoryArray[row]
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return categoryArray[row]
    }
}


// close keyboard
extension UIViewController {
    func hideKeyboardWhenTappedAround() {
        let tap: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(UIViewController.dismissKeyboard))
        tap.cancelsTouchesInView = false
        view.addGestureRecognizer(tap)
    }

    @objc func dismissKeyboard() {
        view.endEditing(true)
    }
}
