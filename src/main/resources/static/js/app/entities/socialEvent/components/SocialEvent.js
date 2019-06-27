import React from 'react';
import {EntitiesServices} from '../../globalServices/EntitiesServices'
import {Container,Row, Col} from 'react-bootstrap';




export class SocialEvent extends React.Component {


  constructor(props){
    super()
    this.state = {
      hide: true,
      dropdownClass: "d-none",//d-none/ d-block
      toggleAreaClass : "",
      toggleAreaMsg:"לפרטים נוספים על האירוע",
      arrowIcon: <span>&#x25BC;</span>

    }
  }
  toggleView = () =>{

    let classA = "";//( this.state.hide ?( "d-block" ): "d-none" );
    let classB , msg, arrow;

    if (this.state.hide ) {//So now the user want to expose it  :
      classA = "d-block" ;//class of the closed area
      classB = "yellow-font"; //class of the area of the toggling collapse todo - u can use in the BS collapse
      msg = "לסגירת פרטי האירוע";
      arrow = <span>&#x25B2;</span>;




    }
    else{//The user closing the block
      classA = "d-none" ;//class of the closed area
      classB = ""; //class of the area of the toggling collapse todo - u can use in the BS collapse
      msg = "לפרטים נוספים על האירוע";
      arrow = <span>&#x25BC;</span>
    }


    this.setState({
      hide: !this.state.hide,
      dropdownClass: classA,
      toggleAreaClass : classB,
      toggleAreaMsg: msg,
      arrowIcon: arrow

    });
  }
  render(){
    /*Some vanilla Js for that component - NOT working*/
    let display = true;
    let data = this.props.data;

    //get the data in format dd/mm/yy
    let today = this.props.data.date;
    let dd = today.getDate();
    let mm = today.getMonth() + 1; //January is 0!
    let yy = today.getYear().toString();
    let fullDate = dd + "/" + mm +"/" +yy.substring(1);



    return(
      <div>
        <Container className = "items-list">
          <Row className = "event-item">{/*<!--start of row of item-->*/}
            <Col>{/*<!--start of columns of item-->*/}
              <Row className = " items-data-row mt-2">
                <Col  xs = "auto" className ="d-none d-md-inline">
                  <Row className = "">
                    <Col className = "col"><img src= {data.imgUrl} alt="picture"  className = "img"/></Col>
                  </Row>
                </Col>

                <Col xs = {6} lg = {4} className = "items-data">
                  <Row className = ""><Col xs = {12} className = ""><h3>{data.name}</h3></Col></Row>
                  <Row className = "">
                    <Col xs ={6} className = "">{data.area}</Col>
                    <Col xs ={6} className = "">
                      <span>לגילאי </span>
                        <span> {data.agesRange[0]} </span>
                        <span> - </span>
                        <span> {data.agesRange[1]} </span>
                    </Col>
                  </Row>


                </Col>
                <Col className = "items-data"></Col>
                <Col xs = {4} className = "items-data">
                  <Row className = "">
                    <Col className = "left-align">
                    <span>יתקיים ב - </span>

                    <span> {fullDate}</span>


                    </Col>
                  </Row>
                  <Row className = "">
                    <Col className = "left-align">
                    <span>מיועד ל-</span>
                    <span>{data.jewLvlKeep+""}</span>
                    </Col>
                  </Row>

                </Col>

              </Row>
              <Row className = "mb-2">

                <Col xs ={8} className = "">
                  <Row className = "items-data-row">
                    <Col xs = "auto" className = "item-label">סוג האירוע:</Col>
                    <Col className = "">{data.eventType+""}</Col>

                  </Row>

                  <Row className = "items-data-row">
                    <Col xs = "auto" className = "item-label">רעיון ההכירות:</Col>
                    <Col className = "">{data.matchingIdea + " "}</Col>
                  </Row>
                  <Row className = "items-data-row">
                    <Col xs = {2} className = "col-2-auto item-label">שעת האירוע:</Col>
                    <Col className = "">
                      <span>{data.time[0]}</span>
                      <span>:</span>
                      <span>{data.time[1]}</span>

                    </Col>
                  </Row>

                </Col>
                  <Col className = ""></Col>
                  <Col xs = {1} className ="d-md-none">
                  <Row className = "">
                    <Col className = "mobile-view"><img src={data.imgUrl} alt="picture"  className = "img"/></Col>
                  </Row>
                </Col>

              </Row>

              {/*<!-- dropdown view -->*/}
              <div id = "dropdown-area" className = {this.state.dropdownClass}>

                <Row className = "items-data-row">
                  <Col xs = "auto" className = "item-label"><u>קצר ולעניין - </u></Col>
                </Row>
                <Row className = "items-data-row ">
                  <Col>
                    <p>
                      {data.shortDescription}
                    </p>
                  </Col>
                </Row>
                <Row className = "items-data-row">
                  <Col xs = "auto" className = "item-label">יש סלקציות ?</Col>
                  <Col>{data.lowSelection+""}</Col>
                </Row>

                <Row className = "items-data-row">
                  <Col xs = "auto" className = "item-label">כתובת - </Col>
                  <Col className = "">{data.address+""}</Col>
                </Row>
                <Row className = "items-data-row">
                  <Col xs = "auto" className = "item-label">מחיר - </Col>
                  <Col className = "">

                    <span> {data.price } </span>
                    <span> ש"ח </span>
                  </Col>
                </Row>
                <Row className = "items-data-row">
                  <Col xs = "auto" className = "item-label">פרטים ליצירת קשר  - </Col>
                  <Col xs = "auto" className = "">{this.props.data.contactDetails [0]}</Col>
                  <Col xs = "auto" className = ""><a href={"tel:" + this.props.data.contactDetails [1]}


                    >
                      {this.props.data.contactDetails [1]}</a>
                  </Col>
                  <Col xs = "auto" className = "">
                    <a href={"mailto:" + this.props.data.contactDetails [2]} target = "_blank">{this.props.data.contactDetails [2]}</a>
                  </Col>

                </Row>

                <Row className = "items-data-row">
                  <Col xs = "auto" className = "item-label">לינק לפרטים ו/או הרשמה <br/> (ייפתח בחלון חדש) - </Col>
                  <Col className = ""><a href = {data.eventLink} target = "_blank">לחץ כאן</a></Col>
                </Row>
                <Row className = "items-data-row">
                  <Col xs  = "auto" className = " item-label">תיאור נוסף באריכות : </Col>
                  <Col className = "">
                    <p>

                      {data.longDescription}
                  </p>
                  </Col>
                </Row>
                <Row className = "items-data-row mb-1">
                  <Col xs = "auto" className = "">פורסם ע"י -</Col>
                  <Col xs = "auto" className = "">{data.publisherName}</Col>
                  <Col xs = "auto" className = "">
                    {!(data.isOrganizer) ? <span> לא </span> : null}
                    <span>ממארגני האירוע</span>

                  </Col>

                </Row>
              </div>{/*<!--End of dropdown-area  of item-->*/}
              {/*Toggle area */}
              <Row className = { this.state.toggleAreaClass + " open-close"  }    onClick={this.toggleView}  >
                <Col className = ""></Col>
                <Col xs = "auto" className = "">
                  {this.state.toggleAreaMsg}
                </Col>
                <Col xs = "auto" className = ""  >{this.state.arrowIcon}</Col>
              </Row>

            </Col>{/*<!--End of columns  of item-->*/}
          </Row>{/*<!--End of row of item-->*/}
        </Container>








      </div>
    );
  }

}
