import { Component, OnInit } from '@angular/core';
import { NavService } from 'src/app/services/nav/nav.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  // allData = ""

  ngOnInit(): void {
    // console.log(this.allData)
    this.nav.show()
  }

  constructor(private nav: NavService){}

  allData = JSON.parse(localStorage.getItem('user')!)

  featuresArray = [
    {
      featureName: "Different Templates",
      featureDescripttion: "There are more templates in Innobix for producing bill templates, allowing users to create bills for their own purposes.",
      featureImage: "https://img.icons8.com/external-inipagistudio-lineal-color-inipagistudio/48/000000/external-receipt-retail-store-inipagistudio-lineal-color-inipagistudio.png"
    },
    {
      featureName: "Different Invoices",
      featureDescripttion: "Whereas making such bills is the easiest task in Innobix, it can help the business develop as innovators are more crucial to the company's financial health.",
      featureImage: "https://img.icons8.com/external-vitaliy-gorbachev-lineal-vitaly-gorbachev/48/null/external-invoices-ecommerce-vitaliy-gorbachev-lineal-vitaly-gorbachev.png"
    },
    {
      featureName: "Financial Support",
      featureDescripttion: "As a financial partner, Innobix will enable the business to email investors with invoices and bills created on this website.",
      featureImage: "https://img.icons8.com/emoji/48/null/smiling-face-with-smiling-eyes.png"
    },
  ]

  templatesArray = [
    {
      templateName: "Template 1",
      templateImage: "https://img.freepik.com/free-psd/digital-marketing-live-webinar-corporate-social-media-post-template_202595-505.jpg?w=2000",
    },
    {
      templateName: "Template 2",
      templateImage: "https://img.freepik.com/free-psd/digital-marketing-live-webinar-corporate-social-media-post-template_202595-505.jpg?w=2000",
    },
    {
      templateName: "Template 3",
      templateImage: "https://img.freepik.com/free-psd/digital-marketing-live-webinar-corporate-social-media-post-template_202595-505.jpg?w=2000",
    },
  ]

  show: boolean = false

  showMenu() {
    this.show = !this.show

  }
}


