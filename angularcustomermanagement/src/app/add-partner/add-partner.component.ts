import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { PartnerService } from '../partner.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-partner',
  templateUrl: './add-partner.component.html',
  styleUrls: ['./add-partner.component.css']
})
export class AddPartnerComponent implements OnInit {

  addPartner : FormGroup;
  message;
  constructor(private fb:FormBuilder,private partnerService:PartnerService,private router:Router) { }

  ngOnInit(): void {
    this.addPartner = this.fb.group({
      name:this.fb.control(null,[
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(20),
        Validators.pattern('^[a-zA-Z]+(?: [a-zA-Z]+)*$')
      ]),
      partnerId:this.fb.control(null,[
        Validators.required,
        Validators.pattern('[0-9]+'),
        Validators.maxLength(10)
      ]),
      date:this.fb.control( null,[
        Validators.required,
      ]),
      status:this.fb.control('Active',[
        Validators.required
      ]),
      addresses:this.fb.array([
        this.getAddress()
      ]),
      emails:this.fb.array([
        this.getEmail()
      ]),
      phoneNumbers:this.fb.array([
        this.getPhoneNumber()
      ])
    });
  }

  // address
  getAddress() {
    return this.fb.control(null,[
      Validators.required
    ]);
  }

  get addresses() :FormArray {
   return this.addPartner.get('addresses') as FormArray;
  }

  addAddress() {
    this.addresses.push(this.getAddress())
  }

  removeAddress(i :number) {
    this.addresses.removeAt(i)
  }

  //email
  getEmail() {
    return this.fb.control(null,[
      Validators.required,
    ]);
  }

  get emails() :FormArray {
    return this.addPartner.get('emails') as FormArray;
   }

   addEmail() {
    this.emails.push(this.getEmail())
  }

  removeEmail(i :number) {
    this.emails.removeAt(i)
  }

  //phone number
  getPhoneNumber() {
    return this.fb.control(null,[
      Validators.required,
      Validators.pattern('^(91){1}[1-9]{1}[0-9]{9}$')
    ]);
  }

  get phoneNumbers() :FormArray {
    return this.addPartner.get('phoneNumbers') as FormArray;
   }

   addPhoneNumber() {
    this.phoneNumbers.push(this.getPhoneNumber())
   }

   removePhoneNumber(i :number) {
    this.phoneNumbers.removeAt(i)
  }

  get name() {
    return this.addPartner.get('name');
  }

  get date() {
    return this.addPartner.get('date');
  }

  get status() {
    return this.addPartner.get('status');
  }

  get partnerId() {
    return this.addPartner.get('partnerId');
  }

  registerPartner() {
    console.log(this.addPartner.value);
    this.partnerService.addPartner(this.addPartner.value).subscribe( response => {
      console.log(response);
      if( response.statusCode === 201) {
        this.addPartner.reset();
        this.router.navigateByUrl('/view-partners');
      } else {
        window.scrollTo(0,0);
        this.message = response.message;
        setTimeout( () => {
          this.message = null;
        },3000);
      }
    });
  }

}
