import React, { useState } from 'react';
import filterTitleIcon from './assets/Filter Section Title.png';
import {
  Accordion,
  AccordionItem,
  AccordionItemHeading,
  AccordionItemButton,
  AccordionItemPanel,
} from 'react-accessible-accordion';
import './FilterSidebar.css';

const FilterSidebar = ({ onFilterChange }) => {
  const [expandedPanels, setExpandedPanels] = useState([]);
  const [selectedFilters, setSelectedFilters] = useState({
    position: [],
    experience: [],
    level: [],
    framework: [],
    programmingLanguage: [],
    developmentTools: [],
  });

  const handleToggle = (panel) => {
    setExpandedPanels((prev) =>
      prev.includes(panel)
        ? prev.filter((item) => item !== panel)
        : [...prev, panel]
    );
  };

  const handleCheckboxChange = (filterType, value) => {
    setSelectedFilters((prev) => {
      const newFilters = prev[filterType].includes(value)
        ? prev[filterType].filter((item) => item !== value)
        : [...prev[filterType], value];

      const updatedFilters = { ...prev, [filterType]: newFilters };
      onFilterChange(updatedFilters); 
      return updatedFilters;
    });
  };

  return (
    <div className="filter-sidebar">
      <img src={filterTitleIcon} alt="Filter Icon" />
      <Accordion allowZeroExpanded>
        {[
          'Position',
          'Years of Experience',
          'Level',
          'Framework',
          'Programming Language',
          'Development Tools',
        ].map((title, index) => (
          <AccordionItem
            key={index}
            uuid={title}
            className={expandedPanels.includes(title) ? 'expanded' : ''}
          >
            <AccordionItemHeading>
              <AccordionItemButton onClick={() => handleToggle(title)}>
                {title}
              </AccordionItemButton>
            </AccordionItemHeading>
            <AccordionItemPanel>
              <ul>
                {title === 'Position' && (
                  <>
                    <li>
                      <input
                        type="checkbox"
                        id="scrum-master"
                        onChange={() => handleCheckboxChange('position', 'Scrum Master')}
                      />
                      <label htmlFor="scrum-master">Scrum Master</label>
                    </li>
                    <li>
                      <input
                        type="checkbox"
                        id="analyst"
                        onChange={() => handleCheckboxChange('position', 'Analyst')}
                      />
                      <label htmlFor="analyst">Analyst</label>
                    </li>
                    <li>
                      <input
                        type="checkbox"
                        id="developer"
                        onChange={() => handleCheckboxChange('position', 'Developer')}
                      />
                      <label htmlFor="developer">Developer</label>
                    </li>
                    <li>
                      <input
                        type="checkbox"
                        id="tester"
                        onChange={() => handleCheckboxChange('position', 'Tester')}
                      />
                      <label htmlFor="tester">Tester</label>
                    </li>
                  </>
                )}
                {title === 'Years of Experience' && (
                  <>
                    <li>
                      <input
                        type="checkbox"
                        id="1-2-years"
                        onChange={() => handleCheckboxChange('experience', '1-2 Years')}
                      />
                      <label htmlFor="1-2-years">1-2 Years</label>
                    </li>
                    <li>
                      <input
                        type="checkbox"
                        id="3-4-years"
                        onChange={() => handleCheckboxChange('experience', '3-4 Years')}
                      />
                      <label htmlFor="3-4-years">3-4 Years</label>
                    </li>
                    <li>
                      <input
                        type="checkbox"
                        id="5plus-years"
                        onChange={() => handleCheckboxChange('experience', '5+ Years')}
                      />
                      <label htmlFor="5plus-years">5+ Years</label>
                    </li>
                  </>
                )}
                {title === 'Level' && (
                  <>
                    <li>
                      <input
                        type="checkbox"
                        id="junior"
                        onChange={() => handleCheckboxChange('level', 'Junior')}
                      />
                      <label htmlFor="junior">Junior</label>
                    </li>
                    <li>
                      <input
                        type="checkbox"
                        id="mid"
                        onChange={() => handleCheckboxChange('level', 'Mid')}
                      />
                      <label htmlFor="mid">Mid</label>
                    </li>
                    <li>
                      <input
                        type="checkbox"
                        id="senior"
                        onChange={() => handleCheckboxChange('level', 'Senior')}
                      />
                      <label htmlFor="senior">Senior</label>
                    </li>
                  </>
                )}
                {title === 'Framework' && (
                  <>
                    <li>
                      <input
                        type="checkbox"
                        id="spring-boot"
                        onChange={() => handleCheckboxChange('framework', 'Spring Boot')}
                      />
                      <label htmlFor="spring-boot">Spring Boot</label>
                    </li>
                    <li>
                      <input
                        type="checkbox"
                        id="react"
                        onChange={() => handleCheckboxChange('framework', 'React')}
                      />
                      <label htmlFor="react">React</label>
                    </li>
                    <li>
                      <input
                        type="checkbox"
                        id="angular"
                        onChange={() => handleCheckboxChange('framework', 'Angular')}
                      />
                      <label htmlFor="angular">Angular</label>
                    </li>
                    <li>
                      <input
                        type="checkbox"
                        id="django"
                        onChange={() => handleCheckboxChange('framework', 'Django')}
                      />
                      <label htmlFor="django">Django</label>
                    </li>
                  </>
                )}
                {title === 'Programming Language' && (
                  <>
                    <li>
                      <input
                        type="checkbox"
                        id="java"
                        onChange={() => handleCheckboxChange('programmingLanguage', 'Java')}
                      />
                      <label htmlFor="java">Java</label>
                    </li>
                    <li>
                      <input
                        type="checkbox"
                        id="python"
                        onChange={() => handleCheckboxChange('programmingLanguage', 'Python')}
                      />
                      <label htmlFor="python">Python</label>
                    </li>
                    <li>
                      <input
                        type="checkbox"
                        id="csharp"
                        onChange={() => handleCheckboxChange('programmingLanguage', 'C#')}
                      />
                      <label htmlFor="csharp">C#</label>
                    </li>
                    <li>
                      <input
                        type="checkbox"
                        id="javascript"
                        onChange={() => handleCheckboxChange('programmingLanguage', 'JavaScript')}
                      />
                      <label htmlFor="javascript">JavaScript</label>
                    </li>
                  </>
                )}
                {title === 'Development Tools' && (
                  <>
                    <li>
                      <input
                        type="checkbox"
                        id="intellij"
                        onChange={() => handleCheckboxChange('developmentTools', 'IntelliJ')}
                      />
                      <label htmlFor="intellij">IntelliJ</label>
                    </li>
                    <li>
                      <input
                        type="checkbox"
                        id="vscode"
                        onChange={() => handleCheckboxChange('developmentTools', 'VS Code')}
                      />
                      <label htmlFor="vscode">VS Code</label>
                    </li>
                    <li>
                      <input
                        type="checkbox"
                        id="katalon"
                        onChange={() => handleCheckboxChange('developmentTools', 'Katalon')}
                      />
                      <label htmlFor="katalon">Katalon</label>
                    </li>
                    <li>
                      <input
                        type="checkbox"
                        id="postman"
                        onChange={() => handleCheckboxChange('developmentTools', 'Postman')}
                      />
                      <label htmlFor="postman">Postman</label>
                    </li>
                  </>
                )}
              </ul>
            </AccordionItemPanel>
          </AccordionItem>
        ))}
      </Accordion>
    </div>
  );
};

export default FilterSidebar;
