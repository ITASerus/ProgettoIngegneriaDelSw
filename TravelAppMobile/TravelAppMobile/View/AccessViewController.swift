//
//  AccessViewController.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 30/06/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import UIKit

class AccessViewController: UIViewController {

    @IBOutlet weak var viewBackground: UIView!
    @IBOutlet weak var usernameOrEmailTextField: UITextField!
    @IBOutlet weak var passwordTextField: UITextField!
    @IBOutlet weak var hidePasswordButton: UIButton!
    @IBOutlet weak var logInButton: UIButton!
    
    
    let controller = AccessController()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        usernameOrEmailTextField.delegate = self
        passwordTextField.delegate = self
        
        hidePasswordButton.layer.borderWidth = 1
        hidePasswordButton.layer.borderColor = UIColor.lightGray.cgColor
        
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
        viewBackground.alpha = 1//0.85
    }
    
    @IBAction func hidePasswordButtonPressed(_ sender: Any) {
        if passwordTextField.isSecureTextEntry == true {
            passwordTextField.isSecureTextEntry = false
            hidePasswordButton.setImage(UIImage.init(systemName: "eye.fill", withConfiguration: UIImage.SymbolConfiguration(weight: .bold)), for: .normal)
        } else {
            passwordTextField.isSecureTextEntry = true
            hidePasswordButton.setImage(UIImage.init(systemName: "eye.slash.fill", withConfiguration: UIImage.SymbolConfiguration(weight: .bold)), for: .normal)
        }
    }
    
    @IBAction func logInButtonPressed(_ sender: Any) {
        controller.logInUser(usernameOrEmail: usernameOrEmailTextField.text!, password: passwordTextField.text!) { (isLogged) in
            DispatchQueue.main.async {
                if(isLogged == 1) {
                    self.usernameOrEmailTextField.text = ""
                    self.passwordTextField.text = ""
                    self.passwordTextField.isSecureTextEntry = true
                    self.hidePasswordButton.setImage(UIImage.init(systemName: "eye.slash.fill", withConfiguration: UIImage.SymbolConfiguration(weight: .bold)), for: .normal)
                    
                    self.performSegue(withIdentifier: "LogInSegue", sender: self)
                    self.dismissKeyboard()
                } else {
                    let alertController = UIAlertController(title: "Credenziali errate", message: "Ricontrollare i dati inseriti", preferredStyle: .alert)
                    alertController.addAction(UIAlertAction(title: "Ok", style: .default))
                    self.present(alertController, animated: true, completion: nil)
                }
            }
        }
    }
}

extension AccessViewController: UITextFieldDelegate {
    
    // To resign keyboard when return key is pressed
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        self.view.endEditing(true)
        return false
    }
}
