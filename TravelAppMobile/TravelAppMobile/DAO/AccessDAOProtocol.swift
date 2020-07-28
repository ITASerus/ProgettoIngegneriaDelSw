//
//  AccessDAOProtocol.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 30/06/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation
import UIKit

protocol AccessDAOProtocol {
    
    func signUp(firstName: String, lastName: String, email: String, username: String, gender: String, password: String, image: UIImage, completion: @escaping ([String: Any]) -> Void)
    
    func logIn(usernameOrEmail: String, password: String, completion: @escaping ([String: Any]) -> Void)
}
