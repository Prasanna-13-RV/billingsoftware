import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  // allData = ""

  ngOnInit(): void {
    // console.log(this.allData)
  }

  allData = JSON.parse(localStorage.getItem('user')!)

  featuresArray = [
    {
      featureName: "Different Templates",
      featureDescripttion: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quibusdam, maiores enim ab voluptatem culpa cum eligendi minima dolores magnam officia, nemo, alias molestias non repellendus dolorum. Sapiente non ad nostrum.",
      featureImage: "https://img.icons8.com/emoji/48/null/smiling-face-with-smiling-eyes.png"
    },
    {
      featureName: "Different Templates",
      featureDescripttion: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quibusdam, maiores enim ab voluptatem culpa cum eligendi minima dolores magnam officia, nemo, alias molestias non repellendus dolorum. Sapiente non ad nostrum.",
      featureImage: "https://img.icons8.com/emoji/48/null/smiling-face-with-smiling-eyes.png"
    },
    {
      featureName: "Different Templates",
      featureDescripttion: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quibusdam, maiores enim ab voluptatem culpa cum eligendi minima dolores magnam officia, nemo, alias molestias non repellendus dolorum. Sapiente non ad nostrum.",
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


}
