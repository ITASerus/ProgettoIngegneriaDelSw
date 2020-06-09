//
//  ProfileViewController.swift
//  TravelAppMobile
//
//  Created by Piero Junior Gaetani on 26/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import UIKit
import Foundation



class ProfileViewController: UIViewController, UITextFieldDelegate {
    
    var user: Users! = nil
    var userWithChenges : Users?
    
    @IBOutlet weak var timerWaitSymbol: UIImageView!
    
    @IBOutlet weak var whiteTopViewAlphaOpacity: UIView!
    @IBOutlet weak var whiteMiddleViewAlphaOpacity: UIView!
    
    @IBOutlet weak var realNameTextField: UITextField!
    @IBOutlet weak var realSurnameTextField: UITextField!
    @IBOutlet weak var nickNameTextField: UITextField!
    
    @IBOutlet weak var sexSegmentedControl: UISegmentedControl!
    
    @IBOutlet weak var emailTextFieldLockEver: UITextField!
    
    @IBOutlet weak var nickNameTopLabel: UILabel!
    
    @IBOutlet weak var emailTopLabel: UILabel!
    
    @IBOutlet weak var sexTextFieldLock: UITextField!
    
    
    @IBOutlet weak var cancelChagnesButtonOutlet: UIButton!
    @IBOutlet weak var saveChagesButton: UIButton!
    
    
    @IBOutlet weak var editButtonOutlet: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        realSurnameTextField.isEnabled = false
        
        realNameTextField.isEnabled = false
               
        editButtonOutlet.isHidden = false
        
               
               sexTextFieldLock.isHidden = false
               sexSegmentedControl.isHidden = true
               saveChagesButton.isHidden = true
               cancelChagnesButtonOutlet.isHidden = true
               
                    realNameTextField.borderStyle = UITextField.BorderStyle.none
               realNameTextField.textColor = UIColor.white
               
                    realSurnameTextField.borderStyle = UITextField.BorderStyle.none
               realSurnameTextField.textColor = UIColor.white
               
               nickNameTextField.textColor = UIColor.white
        
        //Looks for single or multiple taps.
        let tap: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: "dismissKeyboard")

//        Uncomment the line below if you want the tap not not interfere and cancel other interactions.
        tap.cancelsTouchesInView = false

        view.addGestureRecognizer(tap)
        
        emailTextFieldLockEver.isEnabled = false
        sexTextFieldLock.isEnabled = false
        realNameTextField.isEnabled = false
        realSurnameTextField.isEnabled = false
        nickNameTextField.isEnabled = false
        
        whiteTopViewAlphaOpacity.backgroundColor = UIColor.white.withAlphaComponent(0.20)
        whiteTopViewAlphaOpacity.isOpaque = true
        
        whiteMiddleViewAlphaOpacity.backgroundColor = UIColor.white.withAlphaComponent(0.20)
        whiteMiddleViewAlphaOpacity.isOpaque = true
        
                let dataUser = try! Data.init(contentsOf: URL.init(string: "http://travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/users/id=19")!)


                do {
                    let decoder: JSONDecoder = JSONDecoder.init()
                     user = try decoder.decode(Users.self, from: dataUser)
                    userWithChenges = user
                    

                    realNameTextField.isUserInteractionEnabled = false
                    
                    nickNameTopLabel.text = user.username
                    emailTopLabel.text = user.email
                    realNameTextField.text = user.realName
                    realSurnameTextField.text = user.realSurname
                    nickNameTextField.text = user.username
                    emailTextFieldLockEver.text = user.email
                    
//                    sexTextFieldLock.text = user.sex
                    sexTextFieldLock.text = "Non specificato"
                    
                    saveChagesButton.isHidden = true
                    
                   
                            
                } catch let e {
                    print(e)
                }
            }
    
    //Calls this function when the tap is recognized.
    @objc override func dismissKeyboard() {
        //Causes the view (or one of its embedded text fields) to resign the first responder status.
        view.endEditing(true)
    }


    func textFieldShouldReturn(userText: UITextField!) -> Bool {
        userText.resignFirstResponder()
        return true;
    }
        


    
    
    
    @IBAction func editButton(_ sender: UIButton) {
        
        editButtonOutlet.isHidden = true
        
             realNameTextField.borderStyle = UITextField.BorderStyle.roundedRect
        realNameTextField.textColor = UIColor.black
        
             realSurnameTextField.borderStyle = UITextField.BorderStyle.roundedRect
        realSurnameTextField.textColor = UIColor.black
        
        sexTextFieldLock.isHidden = true
        sexSegmentedControl.isHidden = false
        saveChagesButton.isHidden = false
        cancelChagnesButtonOutlet.isHidden = false
        
        
        realNameTextField.isEnabled =  true
        realSurnameTextField.isEnabled = true
       
        
    }
    
    @IBAction func cancelButtonAction(_ sender: UIButton) {
        
        self.viewDidLoad()
        
    }
    
    @IBAction func saveChangesButtonAction(_ sender: UIButton) {
        
        
        
       
        
        
       
        putJson()
        
        do {
            
            sleep(1)
        }
        
        self.viewDidLoad()
        
    }
    
    
    
    func putJson(){
        
        let PutUserDizionario : [String:Any] = [

            
            
                "id": 19,
                "email": "mangomerlino@gmail.com",
                "username": "Mago_Merlino90",
                "password": "passwordSicura123",
                "registrationDate": "2020-05-24T17:51:46.000+0000",
                "realName": realNameTextField.text!,
                "realSurname":  realSurnameTextField.text!,
                "viewRealName": true,
                "role": "User"
                
            
        ]
        
        
       

       
        //create put request
       

       
        let url = URL(string: "http://travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/users/email=mangomerlino@gmail.com")!
       var request = URLRequest(url: url)
        request.httpMethod = "PUT"

        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        request.addValue("application/json", forHTTPHeaderField: "Accept")

        //insert json data to request
     

        
        let jsonPutUser = try? JSONSerialization.data(withJSONObject: PutUserDizionario )

        print("Dizionario >")
        print (PutUserDizionario)

        request.httpBody = jsonPutUser
       


        print (String(data: request.httpBody!, encoding: .utf8)!)

        let task = URLSession.shared.dataTask(with: request) { data, response, error in
            guard let data = data, error == nil else {
                print(error?.localizedDescription ?? "No data")
                return
            }
            
            if let response = response as? HTTPURLResponse{
                print("response core: \(response.statusCode)")
               
            }
            print("Ciao")

        }

        task.resume()
    }
    
    

}
