//Mock of three events
export const EVENTS_MOCK = [
  {
    name: "המפגש של המסטל",
    isOrganizer : true,
    publisherName: "Lingar",
    date: new Date('2019-4-19'),
    time: [20,30],//[hour, minutes]
    contactDetails: ["0547-477-637", "lingar@lin.gar"], //tel,email - must one
    jewLvlKeep: false,// false or true - is want to keep Shabbat
    agesRange: [20, 30], //from to
    lowSelection: ["Not"],//arbitrary - can be by phone by look - open variable for discusting people
    shortDescription: "אחת המטרות של הגוף היא לענג אותנו ולשמור על איזון"
      + "וריפוי באופן מתמיד. מה שפוגע בגוף ומונע ממנו ריפוי"
      +" זה מנגנון ההדחקה הרגשי של האגו. ",
    longDescription: "bla bla bla ", //not must maybe to do it with collapse and default values
    matchingIdea: ["ספונטני", "גורם מקשר", "רישום פרטים אצל אחראי "],
    eventLink: "google.com",
    imgUrl: "images/img1.jpg",
    area: "המרכז", //constant
    address:["פתח תקוה, עמל 46.","להכנס בשביל 50 מטר דלך משמאל"] ,//address, horaot hagaaa
    price: 70
  },
  {
    name: "המפגש של המסטל",
    isOrganizer : true,
    publisherName: "Lingar",
    date: new Date('2019-4-19'),
    time: [20,30],//[hour, minutes]
    contactDetails: ["0547-477-637", "lingar@lin.gar"], //tel,email - must one
    jewLvlKeep: false,// false or true - is want to keep Shabbat
    agesRange: [20, 30], //from to
    lowSelection: ["Not"],//arbitrary - can be by phone by look - open variable for discusting people
    shortDescription: "אחת המטרות של הגוף היא לענג אותנו ולשמור על איזון"
      + "וריפוי באופן מתמיד. מה שפוגע בגוף ומונע ממנו ריפוי"
      +" זה מנגנון ההדחקה הרגשי של האגו. ",
    longDescription: "bla bla bla ", //not must maybe to do it with collapse and default values
    matchingIdea: ["ספונטני", "גורם מקשר", "רישום פרטים אצל אחראי "],
    eventLink: "google.com",
    imgUrl: "images/img1.jpg",
    area: "המרכז", //constant
    address:["פתח תקוה, עמל 46.","להכנס בשביל 50 מטר דלך משמאל"] ,//address, horaot hagaaa
    price: 70
  },
  {
    name: "טיו בהרים",
    isOrganizer : true,
    publisherName: "Mister",
    date: new Date('2019-4-19'),
    time: [20,30],//[hour, minutes]
    contactDetails: ["0547-477-637", "lingar@lin.gar"], //tel,email - must one
    jewLvlKeep: false,// false or true - is want to keep Shabbat
    agesRange: [20, 30], //from to
    lowSelection: ["Not"],//arbitrary - can be by phone by look - open variable for discusting people
    shortDescription: "אחת המטרות של הגוף היא לענג אותנו ולשמור על איזון"
      + "וריפוי באופן מתמיד. מה שפוגע בגוף ומונע ממנו ריפוי"
      +" זה מנגנון ההדחקה הרגשי של האגו. ",
    longDescription: "bla bla bla ", //not must maybe to do it with collapse and default values
    matchingIdea: ["ספונטני", "גורם מקשר", "רישום פרטים אצל ","כוס בירה"],
    eventLink: "google.com",
    imgUrl: "images/img1.jpg",
    area: "המרכז", //constant
    address:["פתח תקוה, עמל 46.","להכנס בשביל 50 מטר דלך משמאל"] ,//address, horaot hagaaa
    price: 0
  },
  {
    name: "מאטצ'-אפ",
    isOrganizer : true,
    publisherName: "Mister",
    date: new Date('2019-4-19'),
    time: [20,30],//[hour, minutes]
    contactDetails: ["0547-477-637", "lingar@lin.gar"], //tel,email - must one
    jewLvlKeep: false,// false or true - is want to keep Shabbat
    agesRange: [20, 30], //from to
    lowSelection: ["Not"],//arbitrary - can be by phone by look - open variable for discusting people
    shortDescription: "אחת המטרות של הגוף היא לענג אותנו ולשמור על איזון"
      + "וריפוי באופן מתמיד. מה שפוגע בגוף ומונע ממנו ריפוי"
      +" זה מנגנון ההדחקה הרגשי של האגו. ",
    longDescription: "bla bla bla ", //not must maybe to do it with collapse and default values
    matchingIdea: ["ספונטני", "גורם מקשר", "רישום פרטים אצל ","כוס בירה"],
    eventLink: "google.com",
    imgUrl: "images/img1.jpg",
    area: "המרכז", //constant
    address:["פתח תקוה, עמל 46.","להכנס בשביל 50 מטר דלך משמאל"] ,//address, horaot hagaaa
    price: 0
  }




]
/*
שם האירוע
האם ממארגני האירוע
שם המפרסם
תאריך
שעה
פרטים ליצירת קשר – מייל ו/או טל'
רמת שמירת יהדות
סוג האירוע
גילאים
איזור בארץ
כתובת
סלקציות אם יש
תיאור קצר עד 180 תווים
הדרך/הרעיון/הפורמט של הערב - הדרך ליצירת הכירוות בערב במשפט אחד
תיאור ארוך
לינק לפרטים או הרשמה
תמונה
מחיר

*/
