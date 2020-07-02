//
//  LoggedUserSingleton.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 02/07/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation

public class LoggedUserSingleton {
    static let shared = LoggedUserSingleton()
    
    var loggedUser: User?
    
    private init() { }
    
    func getLoggedUser() -> User? {
        return loggedUser
    }
    
    func setLoggedUser(loggedUser: User, token: String) {
        self.loggedUser = loggedUser
        self.loggedUser?.token = token
    }
    
}
