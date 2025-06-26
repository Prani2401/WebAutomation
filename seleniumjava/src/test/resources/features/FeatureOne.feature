Feature: Testing All posibilities


    Scenario: checking all interctive web posibilities in website
      Given setup webbrowser
       When Launching Browser
      # And Navigating to pages
      # And Using sendkeys to send text to input
      # And testing checkbox and radio
      #And testing dropdown
      #And checking dates
      # And uploading files
      # And testing tables
      #And testing hidden elements
      # And testing search tabs
     # And testing dynamic button
    # And testing alerts
      # And window handles
      # And testing popups
      #  And testing mousehover
      #And Double Click
      # And DragAndDrop
      #And testinig slidbar
      # And testing brokenlinks
      # And testing autosuggestions
      # And taking ss
      # And testing datatables
      # | Name | Place | DOB| Relation |
      # | kanna | narsapur | 08-10-2002 | Husband |
      # | pannu | produtur| 24-01-2002 | Wife |
      # And testing values by Examples <person1> <person2> <relation> <howlonghavebeenmarried>

      #And testing file download
      # And Dimension and Points
      And testing cookies
      

    Examples:
    |person1|person2|relation|howlonghavebeenmarried|
    |pannu|kanna|couples|1|
