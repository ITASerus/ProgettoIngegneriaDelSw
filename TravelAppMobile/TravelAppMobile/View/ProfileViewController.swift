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
    
    @IBOutlet var reviewsCollectionView: UICollectionView!
    var reviewsList = [Review]()
    
    var loggedUser = LoggedUserSingleton.shared.getLoggedUser()
    var profileEditing = false
    @IBOutlet weak var editButton: UIBarButtonItem!
    @IBOutlet var cancelButton: UIBarButtonItem!
    
    let controller = ProfileController()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        reviewsCollectionView.delegate = self
        reviewsCollectionView.dataSource = self
    
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
                        self.loggedUser?.imageDownloaded = UIImageCodable.init(withImage: image!)
                               
                        self.profileImageView.image = self.loggedUser?.imageDownloaded?.getImage()
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
        firstNameTextField.isUserInteractionEnabled = false
        lastNameTextField.isUserInteractionEnabled = false
        usernameTextField.isUserInteractionEnabled = false
        emailTextField.isUserInteractionEnabled = false
        genderSegmentedControl.isUserInteractionEnabled = false
        
        usernameTopLabel.text = loggedUser?.username ?? "Non specificato"
        emailTopLabel.text = loggedUser?.email ?? "Non specificato"
        profileImageView.image = loggedUser?.imageDownloaded?.getImage()
        firstNameTextField.text = loggedUser?.firstName ?? "Non specificato"
        lastNameTextField.text = loggedUser?.lastName ?? "Non specificato"
        usernameTextField.text = loggedUser?.username ?? "Non specificato"
        emailTextField.text = loggedUser?.email ?? "Non specificato"
       
        switch loggedUser?.gender {
        case "M":
            setGenderSegmentedControlContent(profileGender: "M")
            headerView.backgroundColor = UIColor(red: 0.40, green: 0.53, blue: 0.67, alpha: 1.00)
            infoView.backgroundColor = UIColor(red: 0.40, green: 0.53, blue: 0.67, alpha: 1.00)
            break
        case "F":
            setGenderSegmentedControlContent(profileGender: "F")
            headerView.backgroundColor = UIColor(red: 0.45, green: 0.35, blue: 0.47, alpha: 1.00)
            infoView.backgroundColor = UIColor(red: 0.45, green: 0.35, blue: 0.47, alpha: 1.00)
            break
        default:
            setGenderSegmentedControlContent(profileGender: "N")
            headerView.backgroundColor = UIColor(red: 0.75, green: 0.59, blue: 0.32, alpha: 1.00)
            infoView.backgroundColor = UIColor(red: 0.75, green: 0.59, blue: 0.32, alpha: 1.00)
        }
    
        reviewsList = controller.getReviews(userID: loggedUser!.id)
    }
    
    @IBAction func editButtonPressed(_ sender: Any) {
        profileEditing = true
        
        firstNameTextField.textColor = .black
        firstNameTextField.borderStyle = .roundedRect
        firstNameTextField.isUserInteractionEnabled = true
        
        lastNameTextField.textColor = .black
        lastNameTextField.borderStyle = .roundedRect
        lastNameTextField.isUserInteractionEnabled = true
        
        setGenderSegmentedControlContent(profileGender: nil)
        genderSegmentedControl.isUserInteractionEnabled = true
        
        editButton.title = "Salva"
        cancelButton.title = "Annulla"
    }
    
    @IBAction func cancelButtonPressed(_ sender: Any) {
        if(profileEditing) {
            profileEditing = false

            firstNameTextField.textColor = .white
            firstNameTextField.borderStyle = .none
            firstNameTextField.isUserInteractionEnabled = false
               
            lastNameTextField.textColor = .white
            lastNameTextField.borderStyle = .none
            lastNameTextField.isUserInteractionEnabled = false
            
            setGenderSegmentedControlContent(profileGender: loggedUser?.gender)
            genderSegmentedControl.isUserInteractionEnabled = false
               
            editButton.title = "Modifica"
            cancelButton.title = "Log Out"
        } else {
            _ = navigationController?.popViewController(animated: true)
        }
    }
    
    func setGenderSegmentedControlContent(profileGender: String?) {
        switch profileGender {
        case "M":
            genderSegmentedControl.removeAllSegments()
            genderSegmentedControl.insertSegment(withTitle: "Uomo", at: 0, animated: true)
            genderSegmentedControl.selectedSegmentIndex = 0
            break
        case "F":
            genderSegmentedControl.removeAllSegments()
            genderSegmentedControl.insertSegment(withTitle: "Donna", at: 0, animated: true)
            genderSegmentedControl.selectedSegmentIndex = 0
            break
        case "N":
            genderSegmentedControl.removeAllSegments()
            genderSegmentedControl.insertSegment(withTitle: "Non Specificato", at: 0, animated: true)
            genderSegmentedControl.selectedSegmentIndex = 0
            break
        default:
            genderSegmentedControl.removeAllSegments()
            genderSegmentedControl.insertSegment(withTitle: "Uomo", at: 0, animated: true)
            genderSegmentedControl.insertSegment(withTitle: "Donna", at: 1, animated: true)
            genderSegmentedControl.insertSegment(withTitle: "N/A", at: 2, animated: true)
            switch loggedUser?.gender {
            case "M":
                genderSegmentedControl.selectedSegmentIndex = 0
                break
            case "F":
                genderSegmentedControl.selectedSegmentIndex = 1
                break
            default:
                genderSegmentedControl.selectedSegmentIndex = 2
            }
        }
    }
}

extension ProfileViewController: UICollectionViewDelegate, UICollectionViewDataSource {
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return reviewsList.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "ReviewCell", for: indexPath) as! ProfileReviewCell
        
        cell.structureNameLabel.text = reviewsList[indexPath.row].structureName
        cell.reviewTitleLabel.text = reviewsList[indexPath.row].title
        cell.reviewBodyLabel.text = reviewsList[indexPath.row].description
        let points = reviewsList[indexPath.row].points ?? 0.0
        cell.pointsImageView.image = UIImage (imageLiteralResourceName: GeneralReusables.starsImageAssetName(avgPoints: points))
        
        // Manage image
        if (reviewsList[indexPath.row].structureImage != nil) {
            if(reviewsList[indexPath.row].structureDownloadedImage == nil) {
                cell.imageView.image = UIImage.init(named: "DownloadingImageWBlackShade.pdf")
                
                let imageURL = URL(string: reviewsList[indexPath.row].structureImage!)!

                // just not to cause a deadlock in UI!
                DispatchQueue.global().async {
                    let imageData = try? Data(contentsOf: imageURL)

                    let image = UIImage(data: imageData!)
                    DispatchQueue.main.async {
                        self.reviewsList[indexPath.row].structureDownloadedImage = UIImageCodable.init(withImage: image!)
                        
                        cell.imageView.image = self.reviewsList[indexPath.row].structureDownloadedImage?.getImage()
                    }
                }
            } else {
                cell.imageView.image = self.reviewsList[indexPath.row].structureDownloadedImage?.getImage()
            }
        } else {
            let image = UIImage.init(named: "DefaultImageWBlackShade.pdf")
            self.reviewsList[indexPath.row].structureDownloadedImage = UIImageCodable.init(withImage: image!)
            cell.imageView.image = UIImage.init(named: "DefaultImageWBlackShade.pdf")
        }
        
        return cell
    }
    
}


