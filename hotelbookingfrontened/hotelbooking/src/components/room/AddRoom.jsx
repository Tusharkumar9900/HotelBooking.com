import React from 'react'

const AddRoom = () => {
  // State to hold room details (photo, room type, room price)
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

return (
  <div>AddRoom</div> // Placeholder component, UI to be implemented
)
}

export default AddRoom

