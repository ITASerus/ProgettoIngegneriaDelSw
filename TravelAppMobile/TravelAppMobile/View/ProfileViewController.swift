//
//  ProfileViewController.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 09/07/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import UIKit

class ProfileViewController: UIViewController {

    @IBOutlet weak var usernameTopLabel: UILabel!
    @IBOutlet weak var emailTopLabel: UILabel!
    @IBOutlet weak var profileImageView: ShadowRoundedImageView!
    
    
    @IBOutlet weak var firstNameTextField: UITextField!
    @IBOutlet weak var lastNameTextField: UITextField!
    @IBOutlet weak var usernameTextField: UITextField!
    @IBOutlet weak var emailTextField: UITextField!
    @IBOutlet weak var genderSegmentedControl: UISegmentedControl!
    
    @IBOutlet weak var headerView: UIView!
    @IBOutlet weak var infoView: UIView!
    
    var profileEditing = false
    @IBOutlet weak var editButton: UIBarButtonItem!
    @IBOutlet var cancelButton: UIBarButtonItem!

    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.navigationItem.leftBarButtonItem = nil
        
        var loggedUser = LoggedUserSingleton.shared.getLoggedUser()
               
        // Manage image
        if (loggedUser?.image != nil) {
            if(loggedUser?.imageDownloaded == nil) {
                profileImageView.image = UIImage.init(named: "DownloadingImageWBlackShade.pdf")
                
                let imageURL = URL(string: (loggedUser?.image!)!)

                // just not to cause a deadlock in UI!
                DispatchQueue.global().async {
                    let imageData = try? Data(contentsOf: imageURL!)

                    let image = UIImage(data: imageData!)
                    DispatchQueue.main.async {
                        loggedUser?.imageDownloaded = UIImageCodable.init(withImage: image!)
                               
                        self.profileImageView.image = loggedUser?.imageDownloaded?.getImage()
                    }
                }
            } else {
                profileImageView.image = loggedUser?.imageDownloaded?.getImage()
            }
        } else {
            let image = UIImage.init(named: "DefaultImageWBlackShade.pdf")
            loggedUser?.imageDownloaded = UIImageCodable.init(withImage: image!)
            profileImageView.image = UIImage.init(named: "DefaultImageWBlackShade.pdf")
        }
        
        //Initialization components
        firstNameTextField.isEnabled = false
        lastNameTextField.isEnabled = false
        usernameTextField.isEnabled = false
        emailTextField.isEnabled = false
        genderSegmentedControl.isEnabled = false
        
        usernameTopLabel.text = loggedUser?.username ?? "Non specificato"
        emailTopLabel.text = loggedUser?.email ?? "Non specificato"
        profileImageView.image = loggedUser?.imageDownloaded?.getImage()
        firstNameTextField.text = loggedUser?.firstName ?? "Non specificato"
        lastNameTextField.text = loggedUser?.lastName ?? "Non specificato"
        usernameTextField.text = loggedUser?.username ?? "Non specificato"
        emailTextField.text = loggedUser?.email ?? "Non specificato"
        switch loggedUser?.gender {
        case "M":
            genderSegmentedControl.selectedSegmentIndex = 0
            headerView.backgroundColor = UIColor(red: 0.69, green: 0.84, blue: 1.00, alpha: 1.00)
            infoView.backgroundColor = UIColor(red: 0.69, green: 0.84, blue: 1.00, alpha: 1.00)
            break
        case "F":
            genderSegmentedControl.selectedSegmentIndex = 1
            headerView.backgroundColor = UIColor(red: 0.45, green: 0.35, blue: 0.47, alpha: 1.00)
            infoView.backgroundColor = UIColor(red: 0.45, green: 0.35, blue: 0.47, alpha: 1.00)
            break
        default:
            genderSegmentedControl.selectedSegmentIndex = 2
            headerView.backgroundColor = UIColor(red: 1.00, green: 0.82, blue: 0.53, alpha: 1.00)
            infoView.backgroundColor = UIColor(red: 1.00, green: 0.82, blue: 0.53, alpha: 1.00)
        }
        
    }
    
    @IBAction func editButtonPressed(_ sender: Any) {
        profileEditing = true
        self.navigationItem.leftBarButtonItem = self.cancelButton
        
        firstNameTextField.textColor = .black
        firstNameTextField.borderStyle = .roundedRect
        firstNameTextField.isEnabled = true
        
        lastNameTextField.textColor = .black
        lastNameTextField.borderStyle = .roundedRect
        lastNameTextField.isEnabled = true
        genderSegmentedControl.isEnabled = true
        
        editButton.title = "Salva"
    }
    
    @IBAction func cancelButtonPressed(_ sender: Any) {
        profileEditing = false
        self.navigationItem.leftBarButtonItem = nil
               
        firstNameTextField.textColor = .white
        firstNameTextField.borderStyle = .none
        firstNameTextField.isEnabled = false
               
        lastNameTextField.textColor = .white
        lastNameTextField.borderStyle = .none
        lastNameTextField.isEnabled = false
        
        genderSegmentedControl.isEnabled = false
               
        editButton.title = "Modifica"
    }
}
