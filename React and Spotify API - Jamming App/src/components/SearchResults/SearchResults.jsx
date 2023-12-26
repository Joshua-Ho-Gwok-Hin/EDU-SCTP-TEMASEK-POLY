import TrackList from "../TrackList/TrackList";
import './SearchResults.css';

function SearchResults(props) {
    return (
        <div className="side-containers">
            <h3>Search Results</h3>
            <TrackList tracks={props.searchResults} onAdd={props.onAdd} />
        </div>
    )
}

export default SearchResults;