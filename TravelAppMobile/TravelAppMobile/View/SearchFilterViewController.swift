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

    //Star's Outlet
    @IBOutlet weak var star1: UIButton!
    @IBOutlet weak var star2: UIButton!
    @IBOutlet weak var star3: UIButton!
    @IBOutlet weak var star4: UIButton!
    @IBOutlet weak var star5: UIButton!
    var starButtonsArray : [UIButton] = []
    var totalStars : Int = 0
    
    @IBOutlet weak var categoryPickerView: UIPickerView!
    
    //TextFieldOutlet
    @IBOutlet weak var nameTextField: UITextField!
    @IBOutlet weak var placeTextField: UITextField!
    @IBOutlet weak var contactTextField: UITextField!
    @IBOutlet weak var webSiteTextField: UITextField!
    @IBOutlet weak var lowerPriceTextField: UITextField!
    @IBOutlet weak var upperPriceTextField: UITextField!
    
    private let categoryArray = ["---", "Hotel", "Resort","Attività", "Cibo"]
    
    private var resultList = [Structure]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        categoryPickerView.dataSource = self
        categoryPickerView.delegate = self
        
        nameTextField.delegate = self
        placeTextField.delegate = self
        contactTextField.delegate = self
        webSiteTextField.delegate = self
        lowerPriceTextField.delegate = self
        upperPriceTextField.delegate = self
    
        // To add "Invio" (done) button to numPad keyboard
        self.addDoneButtonOnKeyboard()
        
        // To resign keyboard when the user tap outside keyboard
        self.hideKeyboardWhenTappedAround()
    
        // Background management
        let gradient = CAGradientLayer()
        gradient.cornerRadius = 20
        gradient.frame = viewBackground.bounds
        gradient.colors = [UIColor(red: 0.88, green: 0.83, blue: 0.71, alpha: 1.00).cgColor,
                           UIColor(red: 0.94, green: 0.93, blue: 0.87, alpha: 1.00).cgColor]
        viewBackground.layer.cornerRadius = 20
        viewBackground.layer.shadowRadius = 5
        viewBackground.layer.shadowOpacity = 0.5
        viewBackground.layer.shadowOffset = CGSize(width: 0, height: 3)
        viewBackground.layer.insertSublayer(gradient, at: 70)
        viewBackground.alpha = 0.85
        
        starButtonsArray = [star1, star2, star3, star4, star5]
    }
    
    @IBAction func starButtonAction(_ sender: UIButton) {
        if (sender.tag == totalStars) {
            for button: UIButton in starButtonsArray {
                    button.setImage(UIImage(named: "starGrey.pdf"), for: .normal)
            }
            totalStars = 0
        } else {
            for (i, button) in starButtonsArray.enumerated() {
                if i < sender.tag {
                    button.setImage(UIImage(named: "star.pdf"), for: .normal)
                } else {
                    button.setImage(UIImage(named: "starGrey.pdf"), for: .normal)
                }
            }
            totalStars = sender.tag
        }
    }
    
    @IBAction func searchButtonPressed(_ sender: Any) {
        var stringAvgPoints = String()
        if (totalStars > 0 && totalStars < 5){
            stringAvgPoints = stringAvgPoints + String(totalStars) + " e oltre"
        }
        if  (totalStars == 5){
            stringAvgPoints = stringAvgPoints + String(totalStars)
        }
        if  (totalStars == 0){
            stringAvgPoints = stringAvgPoints + "null"
        }
        
        resultList = controller.getStructureByFilter(name: nameTextField.text!,
                                                     place: placeTextField.text!,
                                                     category: categoryArray[categoryPickerView.selectedRow(inComponent: 0)],
                                                     contacts: contactTextField.text!,
                                                     webSite: webSiteTextField.text!,
                                                     lowerPrice: lowerPriceTextField.text!,
                                                     upperPrice: upperPriceTextField.text!,
                                                     avgPoints: stringAvgPoints)
        
        if(!resultList.isEmpty) {
            performSegue(withIdentifier: "resultsSegue", sender: self)
        } else {
            let alertController = UIAlertController(title: "Nessuna corrispondenza trovata", message:
                "Non è stata trovata nessuna struttura o attrazione che corrisponde ai parametri inseriti.", preferredStyle: .alert)
            alertController.addAction(UIAlertAction(title: "Ok", style: .default))

            self.present(alertController, animated: true, completion: nil)
        }
        
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "resultsSegue" {
            let destinationViewController = segue.destination as! ResultsViewController

            destinationViewController.structureList = resultList
        }
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

// Close keyboard
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

extension SearchFilterViewController: UITextFieldDelegate {
    
    // To resign keyboard when return key is pressed
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        self.view.endEditing(true)
        return false
    }
    
    // To add "Invio" (done) button to numPad keyboard
    func addDoneButtonOnKeyboard() {
        let doneToolbar: UIToolbar = UIToolbar(frame: CGRect.init(x: 0, y: 0, width: 320, height: 50))
        doneToolbar.barStyle = UIBarStyle.default

        let flexSpace = UIBarButtonItem(barButtonSystemItem: UIBarButtonItem.SystemItem.flexibleSpace, target: nil, action: nil)
        let done: UIBarButtonItem = UIBarButtonItem(title: "Invio", style: UIBarButtonItem.Style.done, target: self, action: #selector(doneButtonAction))

        var items = [UIBarButtonItem]()
        items.append(flexSpace)
        items.append(done)

        doneToolbar.items = items
        doneToolbar.sizeToFit()

        self.lowerPriceTextField.inputAccessoryView = doneToolbar
        self.upperPriceTextField.inputAccessoryView = doneToolbar
    }

    @objc func doneButtonAction() {
        self.lowerPriceTextField.resignFirstResponder()
        self.upperPriceTextField.resignFirstResponder()
    }
}
