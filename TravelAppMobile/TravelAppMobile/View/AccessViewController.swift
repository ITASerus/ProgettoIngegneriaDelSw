//
//  AccessViewController.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 30/06/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import UIKit

class AccessViewController: UIViewController {

    @IBOutlet weak var usernameOrEmailTextField: UITextField!
    @IBOutlet weak var passwordTextField: UITextField!
    
    @IBOutlet weak var logInButton: UIButton!
    
    
    let controller = AccessController()
    
    override func viewDidLoad() {
        super.viewDidLoad()

    }
    
    @IBAction func logInButtonPressed(_ sender: Any) {
        controller.logInUser(usernameOrEmail: usernameOrEmailTextField.text!, password: passwordTextField.text!) { (isLogged) in
            DispatchQueue.main.async {
                if(isLogged == 1) {
                    self.performSegue(withIdentifier: "LogInSegue", sender: self)
                } else {
                    let alertController = UIAlertController(title: "Credenziali errate", message: "Ricontrollare i dati inseriti", preferredStyle: .alert)
                    alertController.addAction(UIAlertAction(title: "Ok", style: .default))
                    self.present(alertController, animated: true, completion: nil)
                }
            }
        }
    }
}
