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
    
    func logInUser(usernameOrEmail: String, password: String) {
        AccessController.accessDAO.logIn(usernameOrEmail: usernameOrEmail, password: password)
    }
}
