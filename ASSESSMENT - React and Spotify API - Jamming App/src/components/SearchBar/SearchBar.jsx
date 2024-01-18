import { useState, useCallback } from 'react';
import './SearchBar.css';

function SearchBar(props) {

    const [term, setTerm] = useState("");

    const handleTermChange = useCallback((event) => {
        setTerm(event.target.value);
    }, []);

    const search = useCallback(() => {
        props.onSearch(term);
    }, [props.onSearch, term]);

    return (
        <div className="full-width">
            <div className='search-form'>
                <input className='search-input' type="text" onChange={handleTermChange} />
                <button onClick={search}>Search</button>
            </div>
        </div>
    )
}

export default SearchBar;