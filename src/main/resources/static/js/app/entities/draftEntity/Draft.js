import React from 'react';
//import { Link, Router } from 'react-router-dom'
import Button from 'react-bootstrap/Button';
import {Container,Row, Col, Form, Fade} from 'react-bootstrap';
import a from  'bootstrap-select';
import Select from 'react-select';

export class Draft1 extends React.Component{

  constructor(props) {
    super(props);

    this.state = {
      open: false,
    };
    console.log("context = ");
  }

  render(){
    const open = this.state.open;


    return(
      <div>
        <h1>I am draft 1 -  </h1>

        <CrudBoard/>
        <Filter/>
        <EventsListReactBS/>

        <Button
          onClick={() => this.setState({ open: !open })}
          aria-controls="example-fade-text"
          aria-expanded={open}
        >
          Toggle text
        </Button>
        <Fade in={this.state.open}>
          <div id="example-fade-text">
            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus
            terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer
            labore wes anderson cred nesciunt sapiente ea proident.
          </div>
        </Fade>
      </div>
    )
  }
}
class EventsList extends React.Component{

  constructor(props){
    super();


  }

  render(){
    return(
      <div onClick = {(this.props.onClick1 ==null ? (e)=> e.preventDefault() :()=> this.props.onClick1(this.props.data.id))} >
          <h3>I am Events List</h3>


             <div className = "container items-list">
             {/*<!-- Item 1 : -->*/}
               <div className = "row event-item">{/*<!--start of row of item-->*/}
                 <div className = "col">{/*<!--start of columns of item-->*/}
                   <div className = "row items-data-row mt-2">
                     <div className ="col-auto  d-none d-md-inline">
                       <div className = "row">
                         <div className = "col"><img src="images/party1-beers.jpg" alt="picture"  className = "img"/></div>
                       </div>
                     </div>

                     <div className = "col-6 col-lg-4 items-data">
                       <div className = "row"><div className = "col-12"><h3>המפגש-של-לינגר</h3></div></div>
                       <div className = "row">
                         <div className = "col-6">איזור המרכז</div>
                         <div className = "col-6">לגילאי 18-120</div>
                       </div>


                     </div>
                     <div className = "col items-data"></div>
                     <div className = "col-4 items-data">
                       <div className = "row"><div className = "col left-align">יתקיים ב- 13/6/2019</div></div>
                       <div className = "row"><div className = "col left-align">מיועד ל- שומרי שבת, לא שומרי שבת</div></div>

                     </div>

                   </div>
                   <div className = "row mb-2">

                     <div className = "col-8">
                       <div className = "row items-data-row">
                         <div className = "col-2-auto item-label">סוג האירוע:</div>
                         <div className = "col">מסיבה, מפגש, ערב משחקים</div>

                       </div>

                       <div className = "row items-data-row">
                         <div className = "col-2-auto item-label">רעיון ההכירות:</div>
                         <div className = "col">ספונטי, דרך גורם מקשר, אחר</div>
                       </div>
                       <div className = "row items-data-row">
                         <div className = "col-2-auto item-label">שעת האירוע:</div>
                         <div className = "col">20:00</div>
                       </div>

                     </div>
                     <div className = "col"></div>
                     <div className ="col-1 d-md-none">
                       <div className = "row ">
                         <div className = "col  mobile-view"><img src="images/party2-lights.jpg" alt="picture"  className = "img"/></div>
                       </div>
                     </div>

                   </div>

                   {/*<!-- dropdown view -->*/}
                   <div id = "dropdown-area">

                     <div className = "row items-data-row">
                       <div className = "col-2-auto item-label"><u>קצר ולעניין - </u></div>
                     </div>
                     <div className = "row items-data-row">
                       <p>
                         מסיבה בהפרדה, כל חצי שעה ריקודים, בין לבין מינגלינג, ואפשרות להכיר אחלה אנשים באופן ספונטני, תוך כדי נשנוש וכיבודים קלים.
                       </p>
                     </div>
                     <div className = "row items-data-row">
                       <div className = "col-2-auto item-label">כתובת - </div>
                       <div className = "col">פתח תקווה, עמל 46</div>
                     </div>
                     <div className = "row items-data-row">
                       <div className = "col-2-auto item-label">מחיר - </div>
                       <div className = "col">לא נמסר</div>
                     </div>
                     <div className = "row items-data-row">
                       <div className = "col-2-auto item-label">פרטים ליצירת קשר  - </div>
                       <div className = "col-auto">שרה</div>
                       <div className = "col-auto"><a href="tel:0547477637">0547477637</a></div>
                       <div className = "col-auto"><a href="mailto:agaf58@gmal.com">agaf58@gmal.com</a></div>

                     </div>

                     <div className = "row items-data-row">
                       <div className = "col-2-auto item-label">לינק לפרטים ו/או הרשמה <br/> (ייפתח בחלון חדש) - </div>
                       <div className = "col"><a href = "https://yimprogramming.com/" target = "_blank">לחץ כאן</a></div>
                     </div>
                     <div className = "row items-data-row">
                       <div className = "col-2-auto item-label">תיאור נוסף באריכות : </div>
                       <div className = "col">
                         <p>

                           משנכנס אדר מרבין בשמחה" היא אמרת חז"ל המרחיבה את גבולות השמחה שבה מאופיין חג הפורים, ורואה בשמחה קו מאפיין של חודש אדר כולו. לפי הוגים אחדים אין מדובר רק בהלכה מעשית, להרבות בשמחה בחודש זה, אלא תכונה שטבועה בזמנו של חודש אדר, שלפי מגילת אסתר היה "הַחֹדֶשׁ אֲשֶׁר נֶהְפַּךְ לָהֶם מִיָּגוֹן לְשִׂמְחָה".[1]

                       </p>
                       </div>
                     </div>
                     <div className = "row items-data-row mb-1">
                       <div className = "col-2-auto ">פורסם ע"י -</div>
                       <div className = "col-2-auto ">לינגר / לא נמסר, </div>
                       <div className = "col-2-auto ">לא ממארגני האירוע</div>

                     </div>
                   </div>{/*<!--End of dropdown-area  of item-->*/}
                   <div className = "row open-close" onclick="switchView()">
                     <div className = "col"></div>
                     <div className = "col-auto">לסגירת פרטי האירוע</div>
                     <div className = "col-auto arrow left-align">&#x25B2;</div>
                   </div>

                 </div>{/*<!--End of columns  of item-->*/}
               </div>{/*<!--End of row of item-->*/}
               {/*<!-- Item 2 : -->*/}
               <div className = "row event-item">{/*<!--start of row of item-->*/}
                 <div className = "col">{/*<!--start of columns of item-->*/}
                   <div className = "row items-data-row mt-2">
                     <div className ="col-auto  d-none d-md-inline">
                       <div className = "row">
                         <div className = "col"><img src="images/party2-lights.jpg" alt="picture"  className = "img"/></div>
                       </div>
                     </div>

                     <div className = "col-6 col-lg-4 items-data">
                       <div className = "row"><div className = "col-12"><h3>המשתה</h3></div></div>
                       <div className = "row">
                         <div className = "col-6">איזור המרכז</div>
                         <div className = "col-6">לגילאי 18-120</div>
                       </div>


                     </div>
                     <div className = "col items-data"></div>
                     <div className = "col-4 items-data">
                       <div className = "row"><div className = "col left-align">יתקיים ב15/3/2019</div></div>
                       <div className = "row"><div className = "col left-align">מיועד ל- שומרי שבת, לא שומרי שבת</div></div>

                     </div>

                   </div>
                   <div className = "row mb-2">

                     <div className = "col-8">
                       <div className = "row items-data-row">
                         <div className = "col-2-auto item-label">סוג האירוע:</div>
                         <div className = "col">סעודת שבת, אחר, פעילות</div>

                       </div>

                       <div className = "row items-data-row">
                         <div className = "col-2-auto item-label">רעיון ההכירות:</div>
                         <div className = "col">ספונטנית ניתן ליצור קשר</div>
                       </div>
                       <div className = "row items-data-row">
                         <div className = "col-2-auto item-label">שעת האירוע:</div>
                         <div className = "col">18:00</div>
                       </div>

                     </div>
                     <div className = "col"></div>
                     <div className ="col-1 d-md-none">
                       <div className = "row ">
                         <div className = "col  mobile-view"><img src="images/party2d-lights.jpg" alt="picture"  className = "img"/></div>
                       </div>
                     </div>

                   </div>

                   {/*<!-- dropdown view -->*/}
                   <div id = "dropdown-area">

                     <div className = "row items-data-row">
                       <div className = "col-2-auto item-label"><u>קצר ולעניין - </u></div>
                     </div>
                     <div className = "row items-data-row">
                       <p>

                         סעודת המשתה המיוחדת עם סדנת צחוק עם ליסה, והנחה עם יהונתן שמיר, בקיצור השתרגנו והפתענו או השתגענו עם ההפקה, אך השמחה גדלה וגדלה.
                       </p>
                     </div>
                     <div className = "row items-data-row">
                       <div className = "col-2-auto item-label">כתובת - </div>
                       <div className = "col">גבעתיים, טייבר 57</div>
                     </div>
                     <div className = "row items-data-row">
                       <div className = "col-2-auto item-label">מחיר - </div>
                       <div className = "col">72 ש"ח"</div>
                     </div>
                     <div className = "row items-data-row">
                       <div className = "col-2-auto item-label">פרטים ליצירת קשר  - </div>
                       <div className = "col-auto">שרה</div>
                       <div className = "col-auto"><a href="tel:0547477637">0547477637</a></div>
                       <div className = "col-auto"><a href="mailto:agaf58@gmal.com">agaf58@gmal.com</a></div>

                     </div>

                     <div className = "row items-data-row">
                       <div className = "col-2-auto item-label">לינק לפרטים ו/או הרשמה <br/> (ייפתח בחלון חדש) - </div>
                       <div className = "col"><a href = "https://yimprogramming.com/" target = "_blank">לחץ כאן</a></div>
                     </div>
                     <div className = "row items-data-row">
                       <div className = "col-2-auto item-label">תיאור נוסף באריכות : </div>
                       <div className = "col">
                         <p>

                           משנכנס אדר מרבין בשמחה" היא אמרת חז"ל המרחיבה את גבולות השמחה שבה מאופיין חג הפורים, ורואה בשמחה קו מאפיין של חודש אדר כולו. לפי הוגים אחדים אין מדובר רק בהלכה מעשית, להרבות בשמחה בחודש זה, אלא תכונה שטבועה בזמנו של חודש אדר, שלפי מגילת אסתר היה "הַחֹדֶשׁ אֲשֶׁר נֶהְפַּךְ לָהֶם מִיָּגוֹן לְשִׂמְחָה".[1]

                       </p>
                       </div>
                     </div>
                     <div className = "row items-data-row mb-1">
                       <div className = "col-2-auto ">פורסם ע"י -</div>
                       <div className = "col-2-auto ">לינגר / לא נמסר, </div>
                       <div className = "col-2-auto ">לא ממארגני האירוע</div>

                     </div>
                   </div>{/*<!--End of dropdown-area  of item-->*/}
                   <div className = "row open-close" onclick="switchView()">
                     <div className = "col"></div>
                     <div className = "col-auto">לסגירת פרטי האירוע</div>
                     <div className = "col-auto arrow left-align">&#x25B2;</div>
                   </div>

                 </div>{/*<!--End of columns  of item-->*/}
               </div>{/*<!--End of row of item-->*/}
               {/*<!-- Item 3 : -->*/}
               <div className = "row event-item">{/*<!--start of row of item-->*/}
               <div className = "col">{/*<!--start of columns of item-->*/}
                 <div className = "row items-data-row mt-2">
                   <div className ="col-auto  d-none d-md-inline">
                     <div className = "row">
                       <div className = "col"><img src="images/party1-beers.jpg" alt="picture"  className = "img"/></div>
                     </div>
                   </div>

                   <div className = "col-6 col-lg-4 items-data">
                     <div className = "row"><div className = "col-12"><h3>המפגש-של-לינגר</h3></div></div>
                     <div className = "row">
                       <div className = "col-6">איזור המרכז</div>
                       <div className = "col-6">לגילאי 18-120</div>
                     </div>


                   </div>
                   <div className = "col items-data"></div>
                   <div className = "col-4 items-data">
                     <div className = "row"><div className = "col left-align">יתקיים ב- 13/6/2019</div></div>
                     <div className = "row"><div className = "col left-align">מיועד ל- שומרי שבת, לא שומרי שבת</div></div>

                   </div>

                 </div>
                 <div className = "row mb-2">

                   <div className = "col-8">
                     <div className = "row items-data-row">
                       <div className = "col-2-auto item-label">סוג האירוע:</div>
                       <div className = "col">מסיבה, מפגש, ערב משחקים</div>

                     </div>

                     <div className = "row items-data-row">
                       <div className = "col-2-auto item-label">רעיון ההכירות:</div>
                       <div className = "col">ספונטי, דרך גורם מקשר, אחר</div>
                     </div>
                     <div className = "row items-data-row">
                       <div className = "col-2-auto item-label">שעת האירוע:</div>
                       <div className = "col">20:00</div>
                     </div>

                   </div>
                   <div className = "col"></div>
                   <div className ="col-1 d-md-none">
                     <div className = "row ">
                       <div className = "col  mobile-view"><img src="images/party1-beers.jpg" alt="picture"  className = "img"/></div>
                     </div>
                   </div>

                 </div>

                 {/*<!-- dropdown view -->*/}
                 <div id = "dropdown-area">

                   <div className = "row items-data-row">
                     <div className = "col-2-auto item-label"><u>קצר ולעניין - </u></div>
                   </div>
                   <div className = "row items-data-row">
                     <p>
                       מסיבה בהפרדה, כל חצי שעה ריקודים, בין לבין מינגלינג, ואפשרות להכיר אחלה אנשים באופן ספונטני, תוך כדי נשנוש וכיבודים קלים.
                     </p>
                   </div>
                   <div className = "row items-data-row">
                     <div className = "col-2-auto item-label">כתובת - </div>
                     <div className = "col">פתח תקווה, עמל 46</div>
                   </div>
                   <div className = "row items-data-row">
                     <div className = "col-2-auto item-label">מחיר - </div>
                     <div className = "col">לא נמסר</div>
                   </div>
                   <div className = "row items-data-row">
                     <div className = "col-2-auto item-label">פרטים ליצירת קשר  - </div>
                     <div className = "col-auto">שרה</div>
                     <div className = "col-auto"><a href="tel:0547477637">0547477637</a></div>
                     <div className = "col-auto"><a href="mailto:agaf58@gmal.com">agaf58@gmal.com</a></div>

                   </div>

                   <div className = "row items-data-row">
                     <div className = "col-2-auto item-label">לינק לפרטים ו/או הרשמה <br/> (ייפתח בחלון חדש) - </div>
                     <div className = "col"><a href = "https://yimprogramming.com/" target = "_blank">לחץ כאן</a></div>
                   </div>
                   <div className = "row items-data-row">
                     <div className = "col-2-auto item-label">תיאור נוסף באריכות : </div>
                     <div className = "col">
                       <p>

                         משנכנס אדר מרבין בשמחה" היא אמרת חז"ל המרחיבה את גבולות השמחה שבה מאופיין חג הפורים, ורואה בשמחה קו מאפיין של חודש אדר כולו. לפי הוגים אחדים אין מדובר רק בהלכה מעשית, להרבות בשמחה בחודש זה, אלא תכונה שטבועה בזמנו של חודש אדר, שלפי מגילת אסתר היה "הַחֹדֶשׁ אֲשֶׁר נֶהְפַּךְ לָהֶם מִיָּגוֹן לְשִׂמְחָה".[1]

                     </p>
                     </div>
                   </div>
                   <div className = "row items-data-row mb-1">
                     <div className = "col-2-auto ">פורסם ע"י -</div>
                     <div className = "col-2-auto ">לינגר / לא נמסר, </div>
                     <div className = "col-2-auto ">לא ממארגני האירוע</div>

                   </div>
                 </div>{/*<!--End of dropdown-area  of item-->*/}
                 <div className = "row open-close" onclick="switchView()">
                   <div className = "col"></div>
                   <div className = "col-auto">לסגירת פרטי האירוע</div>
                   <div className = "col-auto arrow left-align">&#x25B2;</div>
                 </div>

               </div>{/*<!--End of columns  of item-->*/}
             </div>{/*<!--End of row of item-->*/}


           </div>{/*<!--End of container of item-->*/}

           {/*... comment....*/}
           {/*... trying to work with the react components....*/}

           <Container fluid = {true}>
            <Row>
              <Col>I am with react bootstrap</Col>
              <Col>2 of 2</Col>
            </Row>
            <Row>
              <Col>1 of 3</Col>
              <Col>2 of 3</Col>
              <Col>3 of 3</Col>
            </Row>
          </Container>;



      </div>

    )
  }

}

class Filter extends React.Component{
  constructor(props){
    super()
  }

  render(){




    function selectAll(){

    }

    return (

      <div>
        <h2>I am filter</h2>

        <div className = "container filter pb-2" >
          <div className = "row d-md-none">
            <div className = "col">
              <button className="btn btn-warning  m-2" type="button" data-toggle="collapse" data-target="#filter" aria-expanded="false"
              aria-controls="collapseExample">
            פתח    סינון אירועים
              </button>

            </div>

          </div>

          <div id = "filter" className = "collapse d-md-block">
            <form className="needs-validation" novalidate>
              <div className="form-row">

                <div className = "col-12 col-md-4">
                  <label for="validationTooltip02">סוג האירוע</label><br/>
                  <div class="form-group">

                      <select class="selectpicker" multiple data-actions-box="true">
                        <option>מסיבה</option>
                        <option>שיעור</option>
                        <option>מפגש</option>
                        <option>מאטצ'-אפ</option>
                        <option>טיול</option>
                      </select>

                  </div>
                  <div class="valid-tooltip">{/*<!-- In the future maybe -->*/}
                    Looks good!
                  </div>
                </div>

                <div className="col-12 col-md-4">
                  <label for="validationTooltip02">מיועד ל-</label><br/>
                  <div class="form-group">

                    <select class="selectpicker" multiple data-actions-box="true">
                      <option>שומרי שבת</option>
                      <option>לא שומרי שבת</option>
                    </select>



                  </div>
                  <div class="valid-tooltip">
                    Looks good!
                  </div>
                </div>



                  <div className="col-12 col-md-4">
                    <label for="validationTooltip02">איזור בארץ</label><br/>
                    <div class="form-group">

                        <select class="selectpicker" multiple data-actions-box="true">
                          <option>ירושלים</option>
                          <option>המרכז</option>
                          <option>צפון רחוק</option>
                          <option>צפון קרוב</option>
                          <option>דרום</option>
                          <option>אילת והערבה</option>
                          <option>שומרון</option>

                        </select>
                      </div>

                    <div class="valid-tooltip">{/*}<!-- In the future maybe -->*/}
                      Looks good!
                    </div>


                  </div>



                  <div className="col-12 col-md-4">
                    <div className="form-group">
                      <p >לגילאים</p>
                      <div className = "form-row">
                        <div className = "col-6">
                          <label >מ-</label>
                          <input type="number" min = "0" max = "120" className="form-control" value="0" required/>

                        </div>
                        <div className = "col-6">


                          <label >עד -</label>
                          <input type="number" min = "0" max = "120" class="form-control" value="120" required/>
                        </div>

                      </div>
                    </div>

                  </div>


                <div className="d-none d-md-inline col-md-4"></div>
                <div className="col-12 col-md-4">
                  <div className="form-group">
                    <p >בין תאריכים:</p>
                      <div classNameName = "form-row">
                        <div classNameName = "col-6">
                          <label >מ-</label>
                          <input type="date" className="form-control" value="2013-01-08" required/>

                        </div>
                        <div classNameName = "col-6">
                          <label >עד -</label>
                          <input type="date" className="form-control" value="2014-01-08" required/>
                        </div>
                      </div>
                    </div>
                </div>

              </div>{/*<!--end of the form-row--> */}

              <button className="btn btn-primary" type="submit">סנן אירועים</button>
              </form>
          </div>{/* <!--End of filter -->*/}
        </div>


      </div>
    )
  }


}





class EventsListReactBS extends React.Component {


  constructor(props){
    super()
    this.state = {
      hide: true,
      dropdownClass: "d-none"//d-none/ d-block

    }
  }
  toggleView = () =>{

    let classA = ( this.state.hide ? "d-block" : "d-none" );
    this.setState({
      hide: !this.state.hide,
      dropdownClass: classA

    });
  }
  render(){
    /*Some vanilla Js for that component - NOT working*/
    var display = true;



    return(
      <div>

        <h2>I am event with react BS </h2>

        <p>Converting the below vanilla(pure) BS to React BS </p>

        <h3>React BS </h3>

        <Row className = "event-item">{/*<!--start of row of item-->*/}
          <Col>{/*<!--start of columns of item-->*/}
            <Row className = " items-data-row mt-2">
              <Col  xs = "auto" className ="d-none d-md-inline">
                <Row className = "">
                  <Col className = "col"><img src="./images/party2-lights.jpg" alt="picture"  className = "img"/></Col>
                </Row>
              </Col>

              <Col xs = {6} lg = {4} className = "items-data">
                <Row className = ""><Col xs = {12} className = ""><h3>המשתה</h3></Col></Row>
                <Row className = "">
                  <Col xs ={6} className = "">איזור המרכז</Col>
                  <Col xs ={6} className = "">לגילאי 18-120</Col>
                </Row>


              </Col>
              <Col className = "items-data"></Col>
              <Col xs = {4} className = "items-data">
                <Row className = ""><Col className = "left-align">יתקיים ב15/3/2019</Col></Row>
                <Row className = ""><Col className = "left-align">מיועד ל- שומרי שבת, לא שומרי שבת</Col></Row>

              </Col>

            </Row>
            <Row className = "mb-2">

              <Col xs ={8} className = "">
                <Row className = "items-data-row">
                  <Col xs = "auto" className = "item-label">סוג האירוע:</Col>
                  <Col className = "">סעודת שבת, אחר, פעילות</Col>

                </Row>

                <Row className = "items-data-row">
                  <Col xs = "auto" className = "item-label">רעיון ההכירות:</Col>
                  <Col className = "">ספונטנית ניתן ליצור קשר</Col>
                </Row>
                <Row className = "items-data-row">
                  <Col xs = {2} className = "col-2-auto item-label">שעת האירוע:</Col>
                  <Col className = "">18:00</Col>
                </Row>

              </Col>
                <Col className = ""></Col>
                <Col xs = {1} className ="d-md-none">
                <Row className = "">
                  <Col className = "mobile-view"><img src="./images/party2-lights.jpg" alt="picture"  className = "img"/></Col>
                </Row>
              </Col>

            </Row>

            {/*<!-- dropdown view -->*/}
            <div id = "dropdown-area" className = {this.state.dropdownClass}>

              <Row className = "items-data-row">
                <Col xs = "auto" className = "item-label"><u>קצר ולעניין - </u></Col>
              </Row>
              <Row className = "items-data-row">
                <p>

                  סעודת המשתה המיוחדת עם סדנת צחוק עם ליסה, והנחה עם יהונתן שמיר, בקיצור השתרגנו והפתענו או השתגענו עם ההפקה, אך השמחה גדלה וגדלה.
                </p>
              </Row>
              <Row className = "items-data-row">
                <Col xs = "auto" className = "item-label">כתובת - </Col>
                <Col className = "">גבעתיים, טייבר 57</Col>
              </Row>
              <Row className = "items-data-row">
                <Col xs = "auto" className = "item-label">מחיר - </Col>
                <Col className = "">72 ש"ח"</Col>
              </Row>
              <Row className = "items-data-row">
                <Col xs = "auto" className = "item-label">פרטים ליצירת קשר  - </Col>
                <Col xs = "auto" className = "">שרה</Col>
                <Col xs = "auto" className = ""><a href="tel:0547477637">0547477637</a></Col>
                <Col xs = "auto" className = ""><a href="mailto:agaf58@gmal.com">agaf58@gmal.com</a></Col>

              </Row>

              <Row className = "items-data-row">
                <Col xs = "auto" className = "item-label">לינק לפרטים ו/או הרשמה <br/> (ייפתח בחלון חדש) - </Col>
                <Col className = ""><a href = "https://yimprogramming.com/" target = "_blank">לחץ כאן</a></Col>
              </Row>
              <Row className = "items-data-row">
                <Col xs  = "auto" className = " item-label">תיאור נוסף באריכות : </Col>
                <Col className = "">
                  <p>

                    משנכנס אדר מרבין בשמחה" היא אמרת חז"ל המרחיבה את גבולות השמחה שבה מאופיין חג הפורים, ורואה בשמחה קו מאפיין של חודש אדר כולו. לפי הוגים אחדים אין מדובר רק בהלכה מעשית, להרבות בשמחה בחודש זה, אלא תכונה שטבועה בזמנו של חודש אדר, שלפי מגילת אסתר היה "הַחֹדֶשׁ אֲשֶׁר נֶהְפַּךְ לָהֶם מִיָּגוֹן לְשִׂמְחָה".[1]

                </p>
                </Col>
              </Row>
              <Row className = "items-data-row mb-1">
                <Col xs = "auto" className = "">פורסם ע"י -</Col>
                <Col xs = "auto" className = "">לינגר / לא נמסר, </Col>
                <Col xs = "auto" className = "">לא ממארגני האירוע</Col>

              </Row>
            </div>{/*<!--End of dropdown-area  of item-->*/}
            <Row className = "open-close" onClick={this.toggleView}>
              <Col className = ""></Col>
              <Col xs = "auto" className = "">לסגירת פרטי האירוע</Col>
              <Col xs = "auto" className = "arrow left-align">&#x25B2;</Col>
            </Row>

          </Col>{/*<!--End of columns  of item-->*/}
        </Row>{/*<!--End of row of item-->*/}






        <h3>Vanilla BS </h3>
        <div className = "row event-item">{/*<!--start of row of item-->*/}
          <div className = "col">{/*<!--start of columns of item-->*/}
            <div className = "row items-data-row mt-2">
              <div className ="col-auto  d-none d-md-inline">
                <div className = "row">
                  <div className = "col"><img src="images/party2-lights.jpg" alt="picture"  className = "img"/></div>
                </div>
              </div>

              <div className = "col-6 col-lg-4 items-data">
                <div className = "row"><div className = "col-12"><h3>המשתה</h3></div></div>
                <div className = "row">
                  <div className = "col-6">איזור המרכז</div>
                  <div className = "col-6">לגילאי 18-120</div>
                </div>


              </div>
              <div className = "col items-data"></div>
              <div className = "col-4 items-data">
                <div className = "row"><div className = "col left-align">יתקיים ב15/3/2019</div></div>
                <div className = "row"><div className = "col left-align">מיועד ל- שומרי שבת, לא שומרי שבת</div></div>

              </div>

            </div>
            <div className = "row mb-2">

              <div className = "col-8">
                <div className = "row items-data-row">
                  <div className = "col-2-auto item-label">סוג האירוע:</div>
                  <div className = "col">סעודת שבת, אחר, פעילות</div>

                </div>

                <div className = "row items-data-row">
                  <div className = "col-2-auto item-label">רעיון ההכירות:</div>
                  <div className = "col">ספונטנית ניתן ליצור קשר</div>
                </div>
                <div className = "row items-data-row">
                  <div className = "col-2-auto item-label">שעת האירוע:</div>
                  <div className = "col">18:00</div>
                </div>

              </div>
              <div className = "col"></div>
              <div className ="col-1 d-md-none">
                <div className = "row ">
                  <div className = "col  mobile-view"><img src="images/party2d-lights.jpg" alt="picture"  className = "img"/></div>
                </div>
              </div>

            </div>

            {/*<!-- dropdown view -->*/}
            <div id = "dropdown-area">

              <div className = "row items-data-row">
                <div className = "col-2-auto item-label"><u>קצר ולעניין - </u></div>
              </div>
              <div className = "row items-data-row">
                <p>

                  סעודת המשתה המיוחדת עם סדנת צחוק עם ליסה, והנחה עם יהונתן שמיר, בקיצור השתרגנו והפתענו או השתגענו עם ההפקה, אך השמחה גדלה וגדלה.
                </p>
              </div>
              <div className = "row items-data-row">
                <div className = "col-2-auto item-label">כתובת - </div>
                <div className = "col">גבעתיים, טייבר 57</div>
              </div>
              <div className = "row items-data-row">
                <div className = "col-2-auto item-label">מחיר - </div>
                <div className = "col">72 ש"ח"</div>
              </div>
              <div className = "row items-data-row">
                <div className = "col-2-auto item-label">פרטים ליצירת קשר  - </div>
                <div className = "col-auto">שרה</div>
                <div className = "col-auto"><a href="tel:0547477637">0547477637</a></div>
                <div className = "col-auto"><a href="mailto:agaf58@gmal.com">agaf58@gmal.com</a></div>

              </div>

              <div className = "row items-data-row">
                <div className = "col-2-auto item-label">לינק לפרטים ו/או הרשמה <br/> (ייפתח בחלון חדש) - </div>
                <div className = "col"><a href = "https://yimprogramming.com/" target = "_blank">לחץ כאן</a></div>
              </div>
              <div className = "row items-data-row">
                <div className = "col-2-auto item-label">תיאור נוסף באריכות : </div>
                <div className = "col">
                  <p>

                    משנכנס אדר מרבין בשמחה" היא אמרת חז"ל המרחיבה את גבולות השמחה שבה מאופיין חג הפורים, ורואה בשמחה קו מאפיין של חודש אדר כולו. לפי הוגים אחדים אין מדובר רק בהלכה מעשית, להרבות בשמחה בחודש זה, אלא תכונה שטבועה בזמנו של חודש אדר, שלפי מגילת אסתר היה "הַחֹדֶשׁ אֲשֶׁר נֶהְפַּךְ לָהֶם מִיָּגוֹן לְשִׂמְחָה".[1]

                </p>
                </div>
              </div>
              <div className = "row items-data-row mb-1">
                <div className = "col-2-auto ">פורסם ע"י -</div>
                <div className = "col-2-auto ">לינגר / לא נמסר, </div>
                <div className = "col-2-auto ">לא ממארגני האירוע</div>

              </div>
            </div>{/*<!--End of dropdown-area  of item-->*/}
            <div className = "row open-close" onclick="switchView()">
              <div className = "col"></div>
              <div className = "col-auto">לסגירת פרטי האירוע</div>
              <div className = "col-auto arrow left-align">&#x25B2;</div>
            </div>

          </div>{/*<!--End of columns  of item-->*/}
        </div>{/*<!--End of row of item-->*/}


      </div>
    );
  }

}


class CrudBoard extends React.Component{
  constructor(props){
    super()
    this.state = {
      test : ["A", "B"],
      eventType:[],
      test3 : ['1','2']
    }
    this.defaultEventType = [{value:'1-One', label : "HI"},{value:'1-Two',  label : "BYE"},{value:'1-Three',label : "Don't Lie"},{value:'1-Four' , label : "High"}];
  }

  checkMe = (ev) =>{
    let dataArr = this.state.test;
    //ev.length > 0 ? dataArr.push(ev[ev.length-1].value) : dataArr =[];

    this.setState({
      test: ev
    })
  }

  onMultipleChange = (ev, meta) =>{


    let propName = meta.name;
    let dataArr = this.state[propName];


    this.setState({
      [propName]: ev
    })
  }

  onAddMoreValuesChange = (ev, propName) =>{

    let arr1 = this.state[propName];
    arr1[ev.target.name] = ev.target.value;
    this.setState({
      [propName]: arr1
    });

  }

  //onAddMoreValuesSave() - for the mixed inputs
  onAddMoreValuesSave = (dataArr, propName) =>{
    //convert the array to good data array
    let newArr = [];
    dataArr.forEach(function(element) {
      console.log(element);
      newArr.push({
        value: element,
        label: element

      });
    });

    //get the state array
    let arr1 = this.state[propName];

    //concatenate them together
    newArr = arr1.concat(newArr);
    this.setState({
      [propName]: newArr
    });

  }
  addMoreInputs = ( propName) =>{
    let arr1 = this.state[propName];
    arr1.push("new value");
    this.setState({
      [propName]: arr1
    });
  }

  render(){


    let dataArr ={label:'Group One',children:[{value:'1-One'},{value:'1-Two'},{value:'1-Three'},{value:'1-Four',label:'Four Label'}, "sss"]};
    const options = [
      { value: 'chocolate', label: 'Chocolate' },
      { value: 'strawberry', label: 'Strawberry' },
      { value: 'vanilla', label: 'Vanilla' }
    ];
    const obj1 = {
      arrA : [1,2,3]

    }
    let view1 = "";

    let array1 =this.state.eventType;



    array1.forEach(function(element) {
      console.log(element);
      view1 += element.label + ", ";
    });

    let choosedValues = this.state.eventType;

    //alert(choosedValues.length );


    return (

      <div>

        <h2>I am Crud board</h2>
        <AddMoreValues
          values = {this.state.test3}
          onChange = {this.onAddMoreValuesChange}
          name = "test3"
          limitation = {3}
          addMore = {this.addMoreInputs}

        />
        <MutipleInputLimited
          name = "eventType"
          limitation = {2}
          choosedValues = {choosedValues}
          onChange = {this.onMultipleChange}
          optionsList = {this.defaultEventType}

        />
        <MultipleLimitedWithAddMore
          name = "eventType"
          limitation = {1}
          choosedValues = {choosedValues}
          onChange = {this.onMultipleChange}
          optionsList = {this.defaultEventType}
          onAddMoreValuesSave = {this.onAddMoreValuesSave}
          values = {this.state.test3}


        />
        <p> State test(eventType) = {view1}</p>
        <div className = "container filter pb-2" >
          <Row className = "d-md-none">
            <Col className = "col">
              <button className="btn btn-warning  m-2" type="button" data-toggle="collapse" data-target="#filter" aria-expanded="false"
              aria-controls="collapseExample">
            פתח    סינון אירועים
              </button>

            </Col>

          </Row>

          <div id = "crud-board" className = "collapse d-md-block">
            <form className="needs-validation" novalidate>
              <Form.Row className="">

                <Col xs ={12} md = {4} className = "">
                  <label for="validationTooltip02">סוג האירוע</label><br/>
                  <div class="form-group">

                      <select  onChange = {
                        (ev )=>{console.log("ev = " + ev.target.value)} } >
                        <option>מסיבה</option>
                        <option>שיעור</option>
                        <option>מפגש</option>
                        <option>מאטצ'-אפ</option>
                        <option>טיול</option>
                      </select>


                  </div>


                  <div class="valid-tooltip">{/*<!-- In the future maybe -->*/}
                    Looks good!
                  </div>
                </Col>


                <Col xs = {12} md = {4} className="">

                  <MutipleInputLimited
                    name = "eventType"
                    limitation = {2}
                    choosedValues = {choosedValues}
                    onChange = {this.onMultipleChange}
                    optionsList = {this.defaultEventType}

                  />
                  <div class="valid-tooltip">
                    Looks good!
                  </div>
                </Col>
                <Col xs = {12} md = {4} className="">

                  <AddMoreValues
                    values = {this.state.test3}
                    onChange = {this.onAddMoreValuesChange}
                    name = "test3"
                    limitation = {3}
                    addMore = {this.addMoreInputs}

                  />
                  <div class="valid-tooltip">
                    Looks good!
                  </div>
                </Col>
                <div className="col-12 col-md-4">
                  <label for="validationTooltip02">מיועד ל-</label><br/>
                  <div class="form-group">

                    <select class="selectpicker" multiple data-actions-box="true">
                      <option>שומרי שבת</option>
                      <option>לא שומרי שבת</option>
                    </select>



                  </div>
                  <div class="valid-tooltip">
                    Looks good!
                  </div>
                </div>



                  <div className="col-12 col-md-4">
                    <label for="validationTooltip02">איזור בארץ</label><br/>
                    <div class="form-group">

                        <select class="selectpicker" multiple data-actions-box="true">
                          <option>ירושלים</option>
                          <option>המרכז</option>
                          <option>צפון רחוק</option>
                          <option>צפון קרוב</option>
                          <option>דרום</option>
                          <option>אילת והערבה</option>
                          <option>שומרון</option>

                        </select>
                      </div>

                    <div class="valid-tooltip">{/*}<!-- In the future maybe -->*/}
                      Looks good!
                    </div>


                  </div>



                  <div className="col-12 col-md-4">
                    <div className="form-group">
                      <p >לגילאים</p>
                      <div className = "form-row">
                        <div className = "col-6">
                          <label >מ-</label>
                          <input type="number" min = "0" max = "120" className="form-control" value="0" required/>

                        </div>
                        <div className = "col-6">


                          <label >עד -</label>
                          <input type="number" min = "0" max = "120" class="form-control" value="120" required/>
                        </div>

                      </div>
                    </div>

                  </div>


                <div className="d-none d-md-inline col-md-4"></div>
                <div className="col-12 col-md-4">
                  <div className="form-group">
                    <p >בין תאריכים:</p>
                      <div classNameName = "form-row">
                        <div classNameName = "col-6">
                          <label >מ-</label>
                          <input type="date" className="form-control" value="2013-01-08" required/>

                        </div>
                        <div classNameName = "col-6">
                          <label >עד -</label>
                          <input type="date" className="form-control" value="2014-01-08" required/>
                        </div>
                      </div>
                    </div>
                </div>

              </Form.Row>{/*<!--end of the form-row--> */}

              <button className="btn btn-primary" type="submit">סנן אירועים</button>
              </form>
          </div>{/* <!--End of filter -->*/}
        </div>


      </div>
    )
  }


}

class MutipleInputLimited extends React.Component{
  /*
  Make input that have dropdown, with limited choicing options. If the user want to choose more he cannot .


  //array for using the data  :

   const options = [
     { value: 'chocolate', label: 'Chocolate' },
     { value: 'strawberry', label: 'Strawberry' },
     { value: 'vanilla', label: 'Vanilla' }
   ];


  */
  constructor(props){
    super()
  }


  onChange = (ev, meta) => {
    console.log(ev, "ev\n", meta , "meta" );
    if (this.props.choosedValues.length == this.props.limitation && (meta.action === "select-option")) {
      alert("אתה לא יכול לבחור יותר מ-" + this.props.limitation + " אפשרויות"  );
      return;
    }

    this.props.onChange(ev, meta);
  }
  render(){


    return (
      <div>
        <Form.Group class="">
          <Form.Label for="validationTooltip02">מיועד ל-</Form.Label><br/>
          <h3>Trying to make MutipleInputLimited </h3>
          <Select   isMulti
            MultiValueRemove
            options={this.props.optionsList} defaultMenuIsOpen = {false} closeMenuOnSelect = {false}
            placeholder  = "בחר"
            value = {this.props.choosedValues}
            onChange = {
                this.onChange
               }
              name = {this.props.name}
          />



        </Form.Group>
        <div class="valid-tooltip">
          Looks good!
        </div>

        <div>
          {this.props.children}
        </div>



      </div>
    )
  }


}

class AddMoreValues extends React.Component{

  constructor(props){
    super()
    this.state = {


    }
  }

  onChange = (ev) =>{
    this.props.onChange(ev, this.props.name);

  }

  addMore = () =>{

    if(this.props.values.length == this.props.limitation){
      alert("אתה לא יכול להוסיף יותר מ-" +this.props.limitation + " ערכים" );
      return;
    }
    this.props.addMore(this.props.name);

  }

  render(){
    console.log(this.props, "this.props");
    let view1 = this.props.values.map((input , index) => {

      return (


        <Form.Control

              type="text"
              value = {this.props.values[index]}
              onChange = {this.onChange}
              name = {index}
            />
              )
    });
    return(

      <div>
        <h2>Trying to make adding more input , with restriction </h2>
        <Form.Group as={Row} controlId="formPlaintextEmail" name = {this.props.name}>

            <Form.Control plaintext readOnly defaultValue="email@example.com" />

        </Form.Group>
        {view1}


        <Button onClick = {this.addMore} type="button" class="">
          <span class="glyphicon glyphicon-plus">&#x2b; הוספת ערך</span>
        </Button>






      </div>

    )
  }
}


class MultipleLimitedWithAddMore extends React.Component{
  constructor(props){
    super()
    this.state = {
      values: ["z","z"]
    }

  }

  //onAddMoreValuesSave() - don't need saving.
  onAddMoreValuesSave = () =>{

    let arr1 = this.state.values;
    this.props.onAddMoreValuesSave (arr1, this.props.name);

  }

  addMoreInputs = ( propName) =>{

    let arr1 = this.state.values;
    arr1.push("new value");
    this.setState({
      values: arr1
    });
  }



   render(){


     return(
       <div>
         <h3>Mixed of multiple and add more </h3>
         <MutipleInputLimited  {...this.props}>{/*Passing this.props to the child  */}
           {console.log(this.props)}
           <p>??</p>
           <AddMoreValues
             values = {this.state.values}
             onChange = {this.onAddMoreValuesChange}
             name = "test3"
             limitation = {3}
             addMore = {this.addMoreInputs}

           />
           <p>values = {this.state.values + ""}</p>
           <Button
             onClick = {
               this.onAddMoreValuesSave
             }

             > save values</Button>
         </MutipleInputLimited>







       </div>

     )
   }

}
