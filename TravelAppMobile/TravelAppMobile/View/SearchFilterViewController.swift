//
//  SearchFilterViewController.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio, Piero Junior Gaetani on 30/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import UIKit

class SearchFilterViewController: UIViewController {
    
    let controller = SearchFilterController()
    
    @IBOutlet weak var viewBackground: UIView!
    
    private let categoryArray = ["---", "Hotel", "Resort","Attività", "Cibo"]
    var starButtonsArray : [UIButton] = []
    var totalStars : Int = 0
    
    //Star's Outlet
    @IBOutlet weak var star1: UIButton!
    @IBOutlet weak var star2: UIButton!
    @IBOutlet weak var star3: UIButton!
    @IBOutlet weak var star4: UIButton!
    @IBOutlet weak var star5: UIButton!

    @IBOutlet weak var categoryPickerView: UIPickerView!
    
    //TextFieldOutlet
    @IBOutlet weak var nameTextField: UITextField!
    @IBOutlet weak var placeTextField: UITextField!
    @IBOutlet weak var webSiteTextField: UITextField!
    @IBOutlet weak var lowerPriceTextField: UITextField!
    @IBOutlet weak var upperPriceTextField: UITextField!
    @IBOutlet weak var contactTextField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        categoryPickerView.dataSource = self
        categoryPickerView.delegate = self
        
        self.hideKeyboardWhenTappedAround()
        
        starButtonsArray = [star1, star2, star3, star4, star5]
        
        lowerPriceTextField.keyboardType = .asciiCapableNumberPad
        upperPriceTextField.keyboardType = .asciiCapableNumberPad
        
        let gradient = CAGradientLayer()
        gradient.cornerRadius = 20
        gradient.frame = viewBackground.bounds
        gradient.colors = [UIColor(red: 0.88, green: 0.83, blue: 0.71, alpha: 1.00).cgColor, UIColor(red: 0.94, green: 0.93, blue: 0.87, alpha: 1.00).cgColor]

        viewBackground.layer.cornerRadius = 20
        viewBackground.layer.shadowRadius = 5
        viewBackground.layer.shadowOpacity = 0.5
        viewBackground.layer.shadowOffset = CGSize(width: 0, height: 3)
        viewBackground.layer.insertSublayer(gradient, at: 70)
        viewBackground.alpha = 0.85
    }
    
    //Set value of stars
    @IBAction func starButtonAction(_ sender: UIButton) {
        for (i, button) in starButtonsArray.enumerated() {
            if i < sender.tag {
                button.setImage(UIImage(named: "star.pdf"), for: .normal)
            } else {
                button.setImage(UIImage(named: "starGrey.pdf"), for: .normal)
            }
        }
        totalStars = sender.tag
    }
    
    @IBAction func printEndpointGetFinal(_ sender: UIButton) {
        var stringAvgPoints = String()
        if (totalStars > 0 && totalStars < 5){
            stringAvgPoints = stringAvgPoints + String(totalStars) + "%20e%20oltre"
        }
        if  (totalStars == 5){
            stringAvgPoints = stringAvgPoints + String(totalStars)
        }
        if  (totalStars == 0){
            stringAvgPoints = stringAvgPoints + "null"
        }
        
        controller.getStructureByFilter(name: nameTextField.text!,
                                        place: placeTextField.text!,
                                        category: categoryArray[categoryPickerView.selectedRow(inComponent: 0)],
                                        contacts: contactTextField.text!,
                                        webSite: webSiteTextField.text!,
                                        lowerPrice: lowerPriceTextField.text!,
                                        upperPrice: upperPriceTextField.text!,
                                        avgPoints: stringAvgPoints)
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
    
    mutating func removeSpaces() {
        var stringCopy = ""
        for char in self {
            if char == " " {
                stringCopy.append("")
            } else {
                stringCopy.append(char)
            }
        }
        self = stringCopy
    }
}

extension SearchFilterViewController: UIPickerViewDelegate, UIPickerViewDataSource {
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return categoryArray.count
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
