//
//  StructureDetailViewController.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 19/05/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import UIKit

class StructureDetailViewController: UIViewController {

    @IBOutlet weak var categoryLabel: UILabel!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var placeLabel: UILabel!
    @IBOutlet weak var contactsLabel: UILabel!
    @IBOutlet weak var priceLabel: UILabel!
    @IBOutlet weak var descriptionTextView: UITextView!
    
    var structure: Structure!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        categoryLabel.text = structure.category
        nameLabel.text = structure.name
        priceLabel.text = structure.price.description
        placeLabel.text = structure.place
        descriptionTextView.text = structure.description
        contactsLabel.text = structure.contacts
    }
}
