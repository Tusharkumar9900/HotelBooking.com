import axios from "axios"

// Create an instance of axios with a base URL for API calls
export const api = axios.create({
    baseURL: "https://localhost:9192",
})

// Function to add a new room to the database
export async function addRoom(photo, roomType, roomPrice){
        // Initialize a new FormData object to send multipart/form-data
        formData.append('photo', photo)          // Append the photo file
        formData.append('roomType', roomType)    // Append the type of the room
        formData.append('roomPrice', roomPrice)  // Append the price of the room
    
        // Make a POST request to the backend to add the new room
        const response = await api.post("/rooms/add/new-room", formData)
     // Check if the room was successfully created (HTTP status 201)
     if(response.status === 201){
        return true // Return true if the operation was successful
    } else {
        return false // Return false if the operation failed
    }
}

/* This Function gets all room types from the database */
export async function getRoomTypes(){
    try {
        // Make a GET request to fetch room types
        const response = await api.get("/rooms/room-types")
        return response.data // Return the data containing room types
    } catch (error) {
        // Throw an error if the request fails
        throw new Error("Error fetching room types")
    }

}