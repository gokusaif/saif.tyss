import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { CustomerService } from '../customer.service';
import { Router, ActivatedRoute } from '@angular/router';
import { element } from 'protractor';

@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css']
})
export class UpdateCustomerComponent implements OnInit {

  updateCustomer:FormGroup;
  message;
  customer;
  id;
  constructor(private fb:FormBuilder,private customerService:CustomerService,private router:Router,
    private activatedRoute:ActivatedRoute) { 
      activatedRoute.params.subscribe( data => {
        console.log(data.id);
        this.id = data.id;
      });
      activatedRoute.queryParams.subscribe( data => {
        this.customer = data;
        console.log(this.customer);
      });
    }

  ngOnInit(): void {
    this.updateCustomer = this.fb.group({
      id:this.fb.control(this.id),
      name:this.fb.control(this.customer.customerName,[
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(20),
        Validators.pattern('^[a-zA-Z]+(?: [a-zA-Z]+)*$')
      ]),
      status:this.fb.control(this.customer.customerStatus,[
        Validators.required
      ]),
      addresses:this.fb.array([
      ]),
      emails:this.fb.array([
      ]),
      phoneNumbers:this.fb.array([
      ])
    });

    this.customer.customerAddress.forEach(element => {
      this.addAddress()
    });

    this.customer.customerEmail.forEach( element => {
      this.addEmail()
    })

    this.customer.customerPhoneNumber.forEach( element => {
      this.addPhoneNumber()
    })

  }

  // address
  getAddress() {
    return this.fb.control(null,[
      Validators.required
    ]);
  }

  get addresses() :FormArray {
   return this.updateCustomer.get('addresses') as FormArray;
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
    return this.updateCustomer.get('emails') as FormArray;
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
    return this.updateCustomer.get('phoneNumbers') as FormArray;
   }

   addPhoneNumber() {
    this.phoneNumbers.push(this.getPhoneNumber())
   }

   removePhoneNumber(i :number) {
    this.phoneNumbers.removeAt(i)
  }

  get name() {
    return this.updateCustomer.get('name');
  }

  get date() {
    return this.updateCustomer.get('date');
  }

  get status() {
    return this.updateCustomer.get('status');
  }

  get partnerId() {
    return this.updateCustomer.get('partnerId');
  }

  updateCustomerr() {
    console.log(this.updateCustomer.value);
    this.customerService.updateCustomer(this.updateCustomer.value).subscribe( response => {
      console.log(response);
      if( response.statusCode === 201) {
        this.message = response.message;
        window.scrollTo(0,0);
        setTimeout( ()=> {
          this.router.navigateByUrl('/view-customers');
        },3000);
      }
    });
  }
}
