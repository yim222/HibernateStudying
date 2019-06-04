//Mock of three events
export let DEFAULTS_VALUES_MOCK = {
        name: "NA",
        isOrganizer : false, //true or false,
        publisherName: "NA",
        date: new Date(),//new Date('2019-4-19'),
        time: [20,30] ,// [20,30],//[hour, minutes]
        contactDetails: ["NA","NA","NA"], //name, tel,email - must one
        jewLvlKeep:  ["רוצים לשמור שבת", "פחות רוצים לשמור שבת"],// could be both or one of them
        agesRange: [0, 120], //from to
        eventType:[ "מפגש " , "שיעור תורה" , "הרצאה" , "טיול יומי", "ערב משחקים", "מסיבה",  "מאטצ'-אפ", "סדנה" , "נופש", "סופש" , "ספידייט" ,  "הופעה" ],

        lowSelection: ["NA"],//arbitrary  - can be by phone by look - open variable for discusting people
        shortDescription: "NA",//-restricted to 180 char - must
        longDescription: "NA", //not must maybe to do it with collapse and default values
        matchingIdea: ["ספונטנית", "דרך גורם מקשר",  "יתבצע רישום אצל אחראי",  "מפגשים אישיים בפעילות"] ,
        eventLink: "NA",
        imgUrl: "NA",
        area: ["ירושלים", "המרכז", "הצפון הרחוק" , "הצפון הקרוב" , "השפלה" , "הדרום",  "יהודה ושומרון ובקעת הירדן"] , //constant
        address:"NA" ,//address +  horaot hagaaa(not need array for now - regular input
        price: 0,//need to be or number (0 is free ) or null - didn't provided
        datesRange: [new Date(), new Date("2020-04-04")]
      };

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
