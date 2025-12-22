
import './Sidebar.css'
import SidebarItem from "./SidebarItem";
import  { SidebarProps } from '../interfaces/sidebar.d';




export default function Sidebar({items}: SidebarProps) {

    return (
        <div className="sidebar">
            {items.map((item, index )=>(
                <SidebarItem key={index} item={item} />
            ))}

        </div>
    )
}