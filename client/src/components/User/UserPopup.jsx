import './UserForm.css'

export default function UserPopup ({setPopup, request, userId}) {
    return (
        <form onSubmit={() => request(userId)}>
            <h1>Delete User</h1>
            <div className="form-sections">
                <p>Are you sure you want to delete this user</p>
            </div>
            <div className="form-sections">
                <button>Delete</button>
                <button type="button" onClick={() => setPopup(false)}>Cancel</button>
            </div>
        </form>
    )
}