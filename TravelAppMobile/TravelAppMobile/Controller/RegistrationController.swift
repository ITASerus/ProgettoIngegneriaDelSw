//
//  RegistrationController.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 28/07/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation
import UIKit

public class RegistrationController {
    private static let userDAO = DAOFactory.getUserDAO(type: "AWSElasticBeanstalk")
    private static let accessDAO = DAOFactory.getAccessDAO(type: "AWSElasticBeanstalk")
    
    func signUp(firstName: String, lastName: String, email: String, username: String, gender: String, password: String, image: UIImage, completion: @escaping (Int) -> Void) {
        
        RegistrationController.accessDAO.signUp(firstName: firstName, lastName: lastName, email: email, username: username, gender: gender, password: password, image: image) { (responseJSON) in
            
            if((responseJSON["success"] as! Int) == 1) { //User registered
                print("RIUSCITO")
                completion(1)
            } else { //User not registered
                print("ERRORE")
                completion(0)
            }
        }
    }
    
}
