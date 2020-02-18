import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { CustomerService } from '../customer.service';
import { Router, ActivatedRoute } from '@angular/router';
import { PartnerService } from '../partner.service';

@Component({
  selector: 'app-update-partner',
  templateUrl: './update-partner.component.html',
  styleUrls: ['./update-partner.component.css']
})
export class UpdatePartnerComponent implements OnInit {

  updatePartner: FormGroup;
  message;
  partner;
  id;
  constructor(private fb: FormBuilder, private partnerService:PartnerService, private router: Router,
    private activatedRoute: ActivatedRoute) {
    activatedRoute.params.subscribe(data => {
      console.log(data.id);
      this.id = data.id;
    });
    activatedRoute.queryParams.subscribe(data => {
      this.partner = data;
      console.log(this.partner);
    });
  }

  ngOnInit(): void {
    this.updatePartner = this.fb.group({
      id: this.fb.control(this.id),
      name: this.fb.control(this.partner.partnerName, [
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(20),
        Validators.pattern('^[a-zA-Z]+(?: [a-zA-Z]+)*$')
      ]),
      status: this.fb.control(this.partner.partnerStatus, [
        Validators.required
      ]),
      addresses: this.fb.array([
      ]),
      emails: this.fb.array([
      ]),
      phoneNumbers: this.fb.array([
      ])
    });

    this.partner.partnerAddress.forEach(element => {
      this.addAddress()
    });

    this.partner.partnerEmail.forEach(element => {
      this.addEmail()
    })

    this.partner.partnerPhoneNumber.forEach(element => {
      this.addPhoneNumber()
    })

  }

  // address
  getAddress() {
    return this.fb.control(null, [
      Validators.required
    ]);
  }

  get addresses(): FormArray {
    return this.updatePartner.get('addresses') as FormArray;
  }

  addAddress() {
    this.addresses.push(this.getAddress())
  }

  removeAddress(i: number) {
    this.addresses.removeAt(i)
  }

  //email
  getEmail() {
    return this.fb.control(null, [
      Validators.required,
    ]);
  }

  get emails(): FormArray {
    return this.updatePartner.get('emails') as FormArray;
  }

  addEmail() {
    this.emails.push(this.getEmail())
  }

  removeEmail(i: number) {
    this.emails.removeAt(i)
  }

  //phone number
  getPhoneNumber() {
    return this.fb.control(null, [
      Validators.required,
      Validators.pattern('^(91){1}[1-9]{1}[0-9]{9}$')
    ]);
  }

  get phoneNumbers(): FormArray {
    return this.updatePartner.get('phoneNumbers') as FormArray;
  }

  addPhoneNumber() {
    this.phoneNumbers.push(this.getPhoneNumber())
  }

  removePhoneNumber(i: number) {
    this.phoneNumbers.removeAt(i)
  }

  get name() {
    return this.updatePartner.get('name');
  }

  get date() {
    return this.updatePartner.get('date');
  }

  get status() {
    return this.updatePartner.get('status');
  }

  get partnerId() {
    return this.updatePartner.get('partnerId');
  }

  updatePartnerr() {
    console.log(this.updatePartner.value);
    this.partnerService.updatePartner(this.updatePartner.value).subscribe( response => {
      console.log(response);
      if( response.statusCode === 201) {
        this.message = response.message;
        window.scrollTo(0,0);
        setTimeout( ()=> {
          this.router.navigateByUrl('/view-partners');
        },3000);
      }
    });
  }
}
