import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterPartner'
})
export class FilterPartnerPipe implements PipeTransform {

  transform(partners: any[],search: String) {
    if( search === undefined){
      return partners;
    }
    return partners.filter((partner, index) => {
      return partner.name.toLowerCase().includes(search.toLowerCase());
    });
  }

}
