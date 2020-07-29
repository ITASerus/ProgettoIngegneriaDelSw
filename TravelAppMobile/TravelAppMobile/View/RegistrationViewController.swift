//
//  RegistrationViewController.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 28/07/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import UIKit

class RegistrationViewController: UIViewController {

    let controller = RegistrationController()
    var imagePicker: ImagePicker!
    
    @IBOutlet weak var viewBackground: UIView!
    @IBOutlet weak var profileImageView: UIImageView!
    @IBOutlet weak var firstNameTextField: UITextField!
    @IBOutlet weak var lastNameTextField: UITextField!
    @IBOutlet weak var emailTextField: UITextField!
    @IBOutlet weak var usernameTextField: UITextField!
    @IBOutlet weak var genderSegmentedControl: UISegmentedControl!
    @IBOutlet weak var passwordTextField: UITextField!
    @IBOutlet weak var confirmPasswordTextField: UITextField!
    @IBOutlet weak var hidePasswordButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        firstNameTextField.delegate = self
        lastNameTextField.delegate = self
        emailTextField.delegate = self
        usernameTextField.delegate = self
        passwordTextField.delegate = self
        confirmPasswordTextField.delegate = self
        
        hidePasswordButton.layer.borderWidth = 1
        hidePasswordButton.layer.borderColor = UIColor.lightGray.cgColor
        
        // To resign keyboard when the user tap outside keyboard
        self.hideKeyboardWhenTappedAround()
        
        //Startup ImagePicker
        self.imagePicker = ImagePicker(presentationController: self, delegate: self)
        
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
    }
    
    @IBAction func imagePickerButtonPressed(_ sender: Any) {
        self.imagePicker.present(from: sender as! UIView)
    }
    
    @IBAction func hidePasswordButtonPressed(_ sender: Any) {
        if passwordTextField.isSecureTextEntry == true {
            passwordTextField.isSecureTextEntry = false
            confirmPasswordTextField.isSecureTextEntry = false
            hidePasswordButton.setImage(UIImage.init(systemName: "eye.fill", withConfiguration: UIImage.SymbolConfiguration(weight: .bold)), for: .normal)
        } else {
            passwordTextField.isSecureTextEntry = true
            confirmPasswordTextField.isSecureTextEntry = true
            hidePasswordButton.setImage(UIImage.init(systemName: "eye.slash.fill", withConfiguration: UIImage.SymbolConfiguration(weight: .bold)), for: .normal)
        }
    }
    
    @IBAction func confirmButtonPressed(_ sender: Any) {
        if(passwordTextField.text == confirmPasswordTextField.text) {
            controller.signUp(firstName: firstNameTextField.text!, lastName: lastNameTextField.text!, email: emailTextField.text!, username: usernameTextField.text!, gender: genderSegmentedControl.titleForSegment(at: genderSegmentedControl.selectedSegmentIndex)!, password: passwordTextField.text!, image: profileImageView.image!) { (isRegistered) in
                
                DispatchQueue.main.async {
                    if(isRegistered == 1) { //Registration Success
                        print("ALERT OK")
                        let alertController = UIAlertController(title: "Registrazione avvenuta con successo", message: "Accedi con lo username o la mail e la password inserita in fase di registrazione", preferredStyle: .alert)
                        
                        let goToAccess = UIAlertAction.init(title: "Ok", style: .default) { (UIAlertAction) in
                            self.navigationController?.popViewController(animated: true)
                        }
                        
                        alertController.addAction(goToAccess)
                        
                        self.present(alertController, animated: true, completion: nil)
                    } else { //Error
                        let alertController = UIAlertController(title: "Registrazione non avvenuta", message: "Ricontrollare i campi inseriti", preferredStyle: .alert)
                           
                        alertController.addAction(UIAlertAction.init(title: "Ok", style: .default))
                        
                        self.present(alertController, animated: true, completion: nil)
                    }
                }
            }
        } else { //The password and confirm password fields doesn't match
            let alertController = UIAlertController(title: "La password non combacia", message: "Le password inserite nel campo password e conferma password non combaciano", preferredStyle: .alert)
                
            alertController.addAction(UIAlertAction(title: "Ok", style: .default))
                       
            self.present(alertController, animated: true, completion: nil)
            
        }
    }
}

extension RegistrationViewController: UITextFieldDelegate {
    
    // To resign keyboard when return key is pressed
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        self.view.endEditing(true)
        return false
    }
}

//MARK: - ImagePicker Delegate
extension RegistrationViewController: ImagePickerDelegate {
    func didSelect(image: UIImage?) {
        self.profileImageView.image = image
    }
}
