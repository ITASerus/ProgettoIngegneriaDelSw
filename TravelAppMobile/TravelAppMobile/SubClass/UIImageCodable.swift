//
//  UIImageCodavle.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 23/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import Foundation
import UIKit

//UIImage is not confrom to Codable protocol. This is an UIImage wrapper that use Data to store it (Data conforms to Codable Protocol)
struct UIImageCodable: Decodable, Hashable {
       let imageData: Data?
       
       init(withImage image: UIImage) {
           self.imageData = image.pngData()
       }
       
       func getImage() -> UIImage? {
           guard let imageData = self.imageData else {
               return nil
        }
           let image = UIImage(data: imageData)
           
           return image
    }
}
