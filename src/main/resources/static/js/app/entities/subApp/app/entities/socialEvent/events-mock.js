//Mock of three events
export const SOCIAL_EVENTS_MOCK2 = new Map ([

      ["1",{
        name: "המפגש של לינגר",
        isOrganizer : true,
        publisherName: "Lingar",
        date: new Date('2019-4-19'),
        time: [20,30],//[hour, minutes]
        contactDetails: ["שרה","0547-477-637", "lingar@lin.gar"], //tel,email - must one
        jewLvlKeep: ["רוצים לשמור שבת", "לא רוצים לשמור שבת"],// could be both or one of them
        agesRange: [20, 30], //from to
        eventType: ["טיול "],
        lowSelection: ["רק אנשים שלא התקלחו שבועיים"],//arbitrary - can be by phone by look - open variable for discusting people
        shortDescription: "אחת המטרות של הגוף היא לענג אותנו ולשמור על איזון"
          + "וריפוי באופן מתמיד. מה שפוגע בגוף ומונע ממנו ריפוי"
          +" זה מנגנון ההדחקה הרגשי של האגו. ",
        longDescription: "bla bla bla ", //not must maybe to do it with collapse and default values
        matchingIdea: ["ספונטני", "גורם מקשר", "רישום פרטים אצל אחראי "],
        eventLink: "google.com",
        imgUrl: "./images/party2-lights.jpg",
        area: "המרכז", //constant
        address:"האצל 32 תל גיבורים" ,//address +  horaot hagaaa(not need array for now ? )
        price: 70

      }],
      ["2", {
        name: "המפגש של לינגר 222",
        isOrganizer : false,
        publisherName: "Lingar2",
        date: new Date('2019-02-3'),
        time: [19,45],//[hour, minutes]
        contactDetails: ["lingar","0547-477-637", "lingar@lin.gar"], //tel,email - must one
        jewLvlKeep: ["רוצים לשמור שבת", "לא רוצים לשמור שבת"],// could be both or one of them
        agesRange: [26, 35], //from to
        eventType: ["מפגש ", "ערב משחקים"],

        lowSelection: ["רק ישיבת ברזילי"],//arbitrary - can be by phone by look - open variable for discusting people
        shortDescription: "אחת המטרות של הגוף היא לענג אותנו ולשמור על איזון"
          + "וריפוי באופן מתמיד. מה שפוגע בגוף ומונע ממנו ריפוי"
          +" זה מנגנון ההדחקה הרגשי של האגו. ",
        longDescription: "bla bla bla ", //not must maybe to do it with collapse and default values
        matchingIdea: ["ספונטני", "גורם מקשר", "רישום פרטים אצל אחראי "],
      eventLink: "https://www.google.com/",
        imgUrl: "./images/party2-lights.jpg",
        area: "המרכז", //constant
        address:"פתח תקווה, עמל 8 , ללכת בשביל 90 קמ "  ,//address, horaot hagaaa
        price: 70

      }],
      ["3", {
        name: "טיו בהרים",
        isOrganizer : true,
        publisherName: "Mister",
        date: new Date('2013-9-25'),
        time: [16,33],//[hour, minutes]
        contactDetails: ["Hagay","0547-477-637", "lingar@lin.gar"], //tel,email - must one
        jewLvlKeep: ["לא שומרי שבת"],// could be both or one of them
        agesRange: [20, 30], //from to
        eventType: ["מאטצ'אפ' ","שיעור"],

        lowSelection: ["רק יוצאי יחידת קה\"ס (קשיי הסתגלות )"],//arbitrary - can be by phone by look - open variable for discusting people
        shortDescription: "אחת המטרות של הגוף היא לענג אותנו ולשמור על איזון"
          + "וריפוי באופן מתמיד. מה שפוגע בגוף ומונע ממנו ריפוי"
          +" זה מנגנון ההדחקה הרגשי של האגו. ",
        longDescription: "bla bla bla ", //not must maybe to do it with collapse and default values
        matchingIdea: ["ספונטני", "גורם מקשר", "רישום פרטים אצל ","כוס בירה"],
        eventLink: "https://www.yahoo.com/",
        imgUrl: "./images/party1-beers.jpg",
        area: "המרכז", //constant
        address:"כתובת כתובתית ביותר "  ,//address, horaot hagaaa
        price: 0

      }],
      ["4",{
        name: "מאטצ'-אפ",
        isOrganizer : false,
        publisherName: "Mister2",
        date: new Date('2019-6-11'),
        time: [22,30],//[hour, minutes]
        contactDetails: ["Idan","0547-477-637", "lingar@lin.gar"], //tel,email - must one
        jewLvlKeep: ["רוצים לשמור שבת"],// could be both or one of them
        agesRange: [20, 30], //from to
        eventType: ["שיעור תורה ", "ספידייט"],

        lowSelection: ["אין"],//arbitrary - can be by phone by look - open variable for discusting people
        shortDescription:" מצעים חיצוניים העשויים לעורר שמחה הם למשל מוזיקה, ריקודים, שמיעת בדיחות, ארוחה טובה, מסע קניות, קבלת "
          ,
        longDescription: "bla bla bla ", //not must maybe to do it with collapse and default values
        matchingIdea: ["ספונטני", "גורם מקשר", "רישום פרטים אצל ","כוס בירה"],
        eventLink: "https://www.nba.com/",
        imgUrl: "./images/friends.jpg",
        area: "המרכז", //constant
        address:" איפשהו בכדור הארץ  " ,//address, horaot hagaaa
        price: 0

      }
    ]

]);
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
