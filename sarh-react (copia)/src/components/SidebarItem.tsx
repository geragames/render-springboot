import { useState } from "react";
import { SidebarItemProps } from "../interfaces/sidebar";


export default function SidebarItem( {item }: SidebarItemProps) {
    const [isOpen, setIsOpen] = useState(false);
  
    if (item.childrens) {
      return (
        <div className={isOpen ? "sidebar-item open" : "sidebar-item"}>
          <div className="sidebar-title">
            <span>
              { item.icon && <i className={item.icon}></i>}
              {item.title}
            </span>
            <i 
              className="bi-chevron-down toggle-btn"
              onClick={() => setIsOpen(!isOpen)}
               
            ></i>
          </div>
          <div className="sidebar-content">
            {item.childrens.map((child, index) => (
              <SidebarItem key={index} item={child} />
            ))}
          </div>
        </div>
      )
    } else {
      return (
          <a href={item.path || "#"} className="sidebar-item plain">
            { item.icon && <i className={item.icon}></i>}
            {item.title}
          </a>
      )
    }
  
  
  }
  