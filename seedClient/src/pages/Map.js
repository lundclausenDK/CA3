import React from "react"
import { compose, withProps } from "recompose"
import { withScriptjs, withGoogleMap, GoogleMap, Marker } from "react-google-maps"

// from: https://tomchentw.github.io/react-google-maps/
// npm install --save react-google-maps

const MyMapComponent = compose(
    withProps({
        googleMapURL: "https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=geometry,drawing,places",
        loadingElement: <div style={{ height: `100%` }} />,
        containerElement: <div style={{ height: `600px` }} />,
        mapElement: <div style={{ height: `100%` }} />,
        /*geoList: ["56.058363,12.067663","55.5346844,12.067663", "56.021966,10.2598323"]*/
    }),
    withScriptjs,
    withGoogleMap,
)((props) =>
    <GoogleMap
        api="AIzaSyAPL2w6-qEYb83Da8rcYbaXVERKya6kNy0"
        defaultZoom={8}
        defaultCenter={{ lat: 56.15, lng: 10.5 }}
    >

        {props.isMarkerShown && props.geoList.map((item)=>{
            let geoSplit = item.geo.split(',');
            return(<Marker key={item} position={{lat: parseInt(geoSplit[0]), lng: parseInt(geoSplit[1])}} onClick={props.onMarkerClick}/>)
        })}
    </GoogleMap>
)

export default class Map extends React.PureComponent {

        constructor(props){
            super();
        }


    state = {
        isMarkerShown: false,
    }

    componentDidMount() {
        this.delayedShowMarker()
    }

    delayedShowMarker = () => {
        setTimeout(() => {
            this.setState({ isMarkerShown: true })
        }, 3000)
    }

    handleMarkerClick = () => {
        this.setState({ isMarkerShown: false })
        this.delayedShowMarker()
    }

    render() {
        return (
            <MyMapComponent
                isMarkerShown={this.state.isMarkerShown}
                onMarkerClick={this.handleMarkerClick}
                geoList={this.props.geoList}
            />
        )
    }
}