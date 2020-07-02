//
//  AccessController.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 30/06/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation

public class AccessController {
    private static let userDAO = DAOFactory.getUserDAO(type: "AWSElasticBeanstalk")
    private static let accessDAO = DAOFactory.getAccessDAO(type: "AWSElasticBeanstalk")
    
    func logInUser(usernameOrEmail: String, password: String, completion: @escaping (Int) -> Void) {
        AccessController.accessDAO.logIn(usernameOrEmail: usernameOrEmail, password: password) { (responseJSON) in
            
            if(responseJSON["accessToken"] == nil) { //Utente non loggato, errore
                print("Errore")
                completion(0)
            } else { //Utente loggato
                AccessController.userDAO.getByUsernameOrEmail(usernameOrEmail: usernameOrEmail) { (userInstance) in
                    LoggedUserSingleton.shared.setLoggedUser(loggedUser: userInstance!, token: responseJSON["accessToken"] as! String)
                    
                    print(LoggedUserSingleton.shared.getLoggedUser()!.firstName!)
                    print(LoggedUserSingleton.shared.getLoggedUser()!.email!)
                    print(LoggedUserSingleton.shared.getLoggedUser()!.token!)
                    completion(1)
                }
            }
        }
    }
}
