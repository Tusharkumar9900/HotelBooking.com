import React, { useEffect, useState } from 'react'
import { getRoomTypes } from '../utils/ApiFunctions'

// Define the RoomTypeSelector component, receiving props for handling input changes and new room data  
const RoomTypeSelector = ({handleRoomInputChange, newRoom }) => {
    // State to store the list of available room types, initialized with an empty string  
    const[roomTypes, setRoomTypes] = useState([""])
     // State to track whether the new room type input field should be displayed 
    const[showNewRoomTypeInput, setShowNewRoomTypesInput] = useState(false)
    // State to store the user-entered new room type  
    const[newRoomTypes, setNewRoomType] = useState("")


    // useEffect hook to fetch room types when the component mounts 
    useEffect(() =>{
       getRoomTypes().then((data)=>{ // Call API function to fetch room types  
        setRoomTypes(data) // Update state with fetched room types 
       })

    },[]) // Empty dependency array ensures it runs only once when the component mounts  


     // Function to handle changes in the new room type input field 
    const handleNewRoomTypeInputChange =(e) =>{
        setNewRoomType(e.target.value); // Update state with user input 
        }
        // Function to add a new room type to the list 
    const handleAddNewRoomType = () =>{
        if(newRoomTypes !== ""){ // Check if the new room type is not empty  
            setRoomTypes([...roomTypes, newRoomTypes]) // Append the new room type to the existing list  
            setNewRoomType("") // Reset input field 
            setShowNewRoomTypesInput(false) // Hide input field after adding the room type 
        }
    }


  return (
    <>
     {/* Check if there are room types available before rendering the dropdown */}
    {roomTypes.length > 0 && (
        <div>
              {/* Dropdown to select a room type */}
            <select
                id = 'roomType'
                name = 'roomType'
                // value = {newRoom.roomType} {/*Set the value to the selected room type */} 
                value={newRoom?.roomType || ""}
                onChange = {(e) =>{
                    if(e.target.value === "Add New"){ // If user selects "Add New" option
                        setShowNewRoomTypesInput(true) // Show input field for new room type 
                    }else{
                        handleRoomInputChange(e) // Otherwise, call the input change handler
                    }
                
                }}>
                <option value=""> Select room type</option> {/* Default placeholder option */}
                <option value={"Add New"}> Add New</option> {/* Option to add a new room type */}
                 {/* Iterate over room types and create an option for each */}
                {roomTypes.map((type, index)=> (
                    <option key={index} value={type}>
                        {type}
                    </option>
                ))}
            </select>
             {/* Conditionally render the new room type input field if the user chooses "Add New" */}
            {showNewRoomTypeInput && (
                <div className ='input-group'>
                      {/* Input field for entering a new room type */}
                    <input className='form-control' placeholder='Enter a new room type'value={newRoomTypes}  onChange={handleNewRoomTypeInputChange}></input>
                    {/* Button to add the new room type */}
                    <button className='btn btn-hotel' type='button' onClick={handleAddNewRoomType}>Add </button>
                    </div>

            )}
        </div>
    )}
    </>
  )
}


export default RoomTypeSelector; 
