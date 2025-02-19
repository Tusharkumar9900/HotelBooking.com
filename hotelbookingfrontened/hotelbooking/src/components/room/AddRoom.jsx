import React, { useState } from 'react'; // Importing React and useState hook
import { addRoom } from '../utils/ApiFunctions'; // Importing API function to add a room
import  RoomTypeSelector from '../common/RoomTypeSelector';

const AddRoom = () => {
  // State to store room details (photo, room type, room price)
  const [newRoom, setNewRoom] = useState({
    photo: null,
    roomType: "",
    roomPrice: ""
  })
  // State to hold the image preview URL for displaying the selected photo
  const [imagePreview, setImagePreview] = useState("")

  // State to hold a success message after successful form submission
  const [successMessage, setSuccessMessage] = useState("")

  // State to hold an error message if something goes wrong
  const [errorMessage, setErrorMessage] = useState("")

  // Function to handle changes in form inputs for the room details
  const handleRoomInputChange = (e) => {
    const name = e.target.name // Input field's name attribute
    let value = e.target.value // Input field's value

    // Special validation for roomPrice field
    if (name === "roomPrice") {
      if (!isNaN(value)) { // Check if the value is a number
        value = parseInt(value) // Convert string to integer
      } else {
        value = "" // Reset to an empty string if not a valid number
      }
    }
  // Update the newRoom state with the modified input value
  setNewRoom({ ...newRoom, [name]: value })
}

 // Function to handle image selection and update preview
const handleImageChange = (e) =>{
const selectedImage = e.target.files[0] // Get the selected image file
setNewRoom({...newRoom, photo: selectedImage}) // Update state with the selected image
setImagePreview(URL.createObjectURL(selectedImage)) // Generate and set preview URL

}

  // Function to handle form submission
const handleSubmit = async(e) =>{
  e.preventDefault()  // Prevent default form submission behavior
   try{
     // Call API function to add room and await response
    const success = await addRoom(newRoom.photo, newRoom.roomType, newRoom.roomPrice)
      // If room is successfully added, show success message and reset form fields
    if(success !== undefined){
      setSuccessMessage("A New Room added successfully in Database")
      setNewRoom({photo: null, roomType: "", roomPrice: ""})
      setImagePreview("")
      setErrorMessage("")
    }else{
      setErrorMessage("Failed to add room")
    }
   }catch(error){
    setErrorMessage(error.message) // Set error message if request fails
   }

   setTimeout(() => {
    setSuccessMessage("") // Clear success message after 5 seconds
    setErrorMessage("") // Clear error message after 5 seconds
    }, 3000);

}

return (
  <>
  {/* Container for the add room form */}
  <section className = "container, mt-5 mb-5">
  <div className = "row justify-content-center">
    <div className="col-md-8-lg-6">
       {/* Page title */}
      <h2 className = "mt-5 mb-2">Add New Room </h2>
      {successMessage && (
        <div className="alert alert-success fade show" role="alert">
          {successMessage}
        </div>
      )}

{errorMessage && (
        <div className="alert alert-danger fade show" role="alert">
          {errorMessage}
        </div>
      )}

      {/* Room addition form */}
      <form onSubmit ={handleSubmit}>
         {/* Room Type Selector */}
        <div className="mb-3">
          <label htmlFor="roomType" className="form-label"> Room Type
          </label>
<div>
  <RoomTypeSelector handleRoomInputChange={handleRoomInputChange} newRoom={newRoom}/>
</div>
</div>
{/* Room Price Input Field */}
        <div className="mb-3">
          <label htmlFor="roomPrice" className="form-label"> Room Price

          </label>
<input className="form-control"
required id="roomPrice" type="number" name="roomPrice" value={newRoom.roomPrice} onChange={handleRoomInputChange}/>
        </div>
 {/* Room Photo Upload */}
        <div className ="mb-3">
          <label htmlFor="photo" className="form-label"> Room Photo
            </label>
            <input id="photo" name="photo" type="file" className="form-control" onChange={handleImageChange}/>
           {/* Display image preview if available */}
           {imagePreview && (
            <img src={imagePreview} alt="Preview Room image" style={{maxWidth: "400px", maxHeight: "400px"}}
            className="mb-3"/>
           )}
        </div>
 {/* Save Room Button */}
        <div className ="d-grid d-flex mt-2">
        <button className= "btn btn-outline-primary ml-5">
          Save Room
        </button>
        </div>



      </form>
{/* Display success or error messages */}
{successMessage && <p className="text-success">{successMessage}</p>}
            {errorMessage && <p className="text-danger">{errorMessage}</p>}
    </div>

</div>
  </section>
 
  </>



)
}

export default AddRoom

