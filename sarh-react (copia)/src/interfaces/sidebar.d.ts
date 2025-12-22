
export interface SidebarItemType {
    title: string;
    icon?: string;
    path?: string;
    childrens?: SidebarItemType[];
}   

export interface SidebarProps {
    items: SidebarItemType[]; 
}

export interface SidebarItemProps {
    item: SidebarItemType;
}