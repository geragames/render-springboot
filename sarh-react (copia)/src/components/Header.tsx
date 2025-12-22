import "./Header.css"

export const Header = () => {
    return (
        <header className="header">
            <div className="profile">
                <img src="https://unavatar.io/github/leo" alt="Foto de perfil" className="profile-img"/>
                <span className="profile-name">Leonardo</span>
            </div>
            <div className="notifications">
            <span className="notification-icon">&#x1F514;</span> {/* Icono de campana */}
            <span className="badge">3</span>
            </div>
        </header>
    )
}