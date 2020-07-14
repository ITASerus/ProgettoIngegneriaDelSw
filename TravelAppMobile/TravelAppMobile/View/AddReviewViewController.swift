//
//  AddReviewViewController.swift
//  TravelAppMobile
//
//  Created by Ernesto De Crecchio on 13/07/2020.
//  Copyright © 2020 Ernesto De Crecchio. All rights reserved.
//

import UIKit

class AddReviewViewController: UIViewController {

    @IBOutlet weak var titleTextField: UITextField!
    @IBOutlet weak var bodyTextView: UITextView!
    
    //Star's Outlet
    @IBOutlet weak var star1: UIButton!
    @IBOutlet weak var star2: UIButton!
    @IBOutlet weak var star3: UIButton!
    @IBOutlet weak var star4: UIButton!
    @IBOutlet weak var star5: UIButton!
    var starButtonsArray : [UIButton] = []
    var totalStars : Int = 0
    
    var structure: Structure!
    let controller = AddReviewController()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        titleTextField.delegate = self
        bodyTextView.delegate = self
        
        // To add "Conferma" (done) button to textView keyboard
        self.addDoneButtonOnKeyboard()
        
        // To resign keyboard when the user tap outside keyboard
        self.hideKeyboardWhenTappedAround()
        
        //Placeholder for bodyTextView
        bodyTextView.text = "Inserisci una breve descrizione della tua esperienza"
        bodyTextView.textColor = UIColor.lightGray

        bodyTextView.becomeFirstResponder()

        bodyTextView.selectedTextRange = bodyTextView.textRange(from: bodyTextView.beginningOfDocument, to: bodyTextView.beginningOfDocument)
        
        //Border for bodyTextView
        bodyTextView.layer.borderWidth = 1
        bodyTextView.layer.borderColor =  UIColor(red: 0.80, green: 0.80, blue: 0.80, alpha: 1.00).cgColor
        bodyTextView.layer.cornerRadius = 6
        
        starButtonsArray = [star1, star2, star3, star4, star5]
    }
    
    @IBAction func starButtonPressed(_ sender: UIButton) {
        if (sender.tag == totalStars) {
            for button: UIButton in starButtonsArray {
                    button.setImage(UIImage(named: "starGrey.pdf"), for: .normal)
            }
            totalStars = 0
        } else {
            for (i, button) in starButtonsArray.enumerated() {
                if i < sender.tag {
                    button.setImage(UIImage(named: "star.pdf"), for: .normal)
                } else {
                    button.setImage(UIImage(named: "starGrey.pdf"), for: .normal)
                }
            }
            totalStars = sender.tag
        }
    }
    
    @IBAction func confirmButtonPressed(_ sender: Any) {
        let bodyText = bodyTextView.textColor != UIColor.black ? nil : bodyTextView.text
        
        controller.addReview(title: titleTextField.text!, body: bodyText!, points: totalStars, userID: LoggedUserSingleton.shared.getLoggedUser()!.id, structureID: structure.id) { result in
            if result == 1 {
                let alertController = UIAlertController(title: "Errore", message: "La recensione è troppo lunga...sii più diretto! (MAX CARATTERI = 250)", preferredStyle: .alert)
                alertController.addAction(UIAlertAction(title: "Ok", style: .default))
                self.present(alertController, animated: true, completion: nil)
            } else { // Review Added
                DispatchQueue.main.async {
                    self.structure.nReviews = self.structure.nReviews! + 1
                    
                    // Increments the review number in calling view
                    (self.navigationController?.viewControllers[self.navigationController!.viewControllers.count - 2] as! StructureDetailViewController).structure.nReviews! += 1
                    
                    self.navigationController?.popViewController(animated: true)
                }
            }
        }
    }
}

extension AddReviewViewController: UITextFieldDelegate {
    // To resign keyboard when return key is pressed
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        self.view.endEditing(true)
        return false
    }
}

extension AddReviewViewController: UITextViewDelegate {

    // To add "Invio" (done) button to textView keyboard
    func addDoneButtonOnKeyboard() {
        let doneToolbar: UIToolbar = UIToolbar(frame: CGRect.init(x: 0, y: 0, width: 320, height: 50))
        doneToolbar.barStyle = UIBarStyle.default

        let flexSpace = UIBarButtonItem(barButtonSystemItem: UIBarButtonItem.SystemItem.flexibleSpace, target: nil, action: nil)
        let done: UIBarButtonItem = UIBarButtonItem(title: "Conferma", style: UIBarButtonItem.Style.done, target: self, action: #selector(doneButtonAction))

        var items = [UIBarButtonItem]()
        items.append(flexSpace)
        items.append(done)

        doneToolbar.items = items
        doneToolbar.sizeToFit()

        self.bodyTextView.inputAccessoryView = doneToolbar
    }
    
    @objc func doneButtonAction() {
           self.bodyTextView.resignFirstResponder()
       }
    
    
    // Placeholder
    func textView(_ textView: UITextView, shouldChangeTextIn range: NSRange, replacementText text: String) -> Bool {

        // Combine the textView text and the replacement text to
        // create the updated text string
        let currentText:String = textView.text
        let updatedText = (currentText as NSString).replacingCharacters(in: range, with: text)

        // If updated text view will be empty, add the placeholder
        // and set the cursor to the beginning of the text view
        if updatedText.isEmpty {

            textView.text = "Inserisci una breve descrizione della tua esperienza"
            textView.textColor = UIColor.lightGray

            textView.selectedTextRange = textView.textRange(from: textView.beginningOfDocument, to: textView.beginningOfDocument)
        }

        // Else if the text view's placeholder is showing and the
        // length of the replacement string is greater than 0, set
        // the text color to black then set its text to the
        // replacement string
         else if textView.textColor == UIColor.lightGray && !text.isEmpty {
            textView.textColor = UIColor.black
            textView.text = text
        }

        // For every other case, the text should change with the usual
        // behavior...
        else {
            return true
        }

        // ...otherwise return false since the updates have already
        // been made
        return false
    }
}
