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
    
    @IBOutlet weak var profileImageView: UIImageView!
    @IBOutlet weak var firstNameTextField: UITextField!
    @IBOutlet weak var lastNameTextField: UITextField!
    @IBOutlet weak var emailTextField: UITextField!
    @IBOutlet weak var usernameTextField: UITextField!
    @IBOutlet weak var genderSegmentedControl: UISegmentedControl!
    @IBOutlet weak var passwordTextField: UITextField!
    @IBOutlet weak var confirmPasswordTextField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        firstNameTextField.delegate = self
        lastNameTextField.delegate = self
        emailTextField.delegate = self
        usernameTextField.delegate = self
        passwordTextField.delegate = self
        confirmPasswordTextField.delegate = self
        
        // To resign keyboard when the user tap outside keyboard
        self.hideKeyboardWhenTappedAround()
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
