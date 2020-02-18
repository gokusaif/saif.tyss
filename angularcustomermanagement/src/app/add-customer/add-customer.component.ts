import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {

  addCustomer:FormGroup;
  message;
  id;
  constructor(private fb:FormBuilder,private customerService:CustomerService,private router:Router,
    private activatedRoute:ActivatedRoute) { 
      activatedRoute.params.subscribe( data => {
        this.id = data.id;
      });
    }

  ngOnInit(): void {
    this.addCustomer = this.fb.group({
      name:this.fb.control(null,[
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(20),
        Validators.pattern('^[a-zA-Z]+(?: [a-zA-Z]+)*$')
      ]),
      partnerId:this.fb.control(this.id,[
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
   return this.addCustomer.get('addresses') as FormArray;
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
    return this.addCustomer.get('emails') as FormArray;
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
    return this.addCustomer.get('phoneNumbers') as FormArray;
   }

   addPhoneNumber() {
    this.phoneNumbers.push(this.getPhoneNumber())
   }

   removePhoneNumber(i :number) {
    this.phoneNumbers.removeAt(i)
  }

  get name() {
    return this.addCustomer.get('name');
  }

  get date() {
    return this.addCustomer.get('date');
  }

  get status() {
    return this.addCustomer.get('status');
  }

  get partnerId() {
    return this.addCustomer.get('partnerId');
  }

  registerCustomer() {
    console.log(this.addCustomer.value);
    this.customerService.addCustomer(this.addCustomer.value).subscribe( response => {
      console.log(response);
      if( response.statusCode === 201) {
        this.addCustomer.reset();
        this.router.navigateByUrl('/view-customers');
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
