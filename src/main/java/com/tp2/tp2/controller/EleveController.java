package com.tp2.tp2.controller;

import com.tp2.tp2.model.Eleve;
import com.tp2.tp2.service.EleveService;
import com.tp2.tp2.service.FiliereService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/eleves")
public class EleveController extends HttpServlet {

    private EleveService eleveService;
    private FiliereService filiereService;

    @Override
    public void init() throws ServletException {
        this.eleveService = new EleveService(null);
        this.filiereService = new FiliereService(null);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {

            case "new":
                request.setAttribute("filieres", filiereService.findAllFilieres());
                request.getRequestDispatcher("eleves/new.jsp").forward(request, response);
                break;

            case "edit":
                int idEdit = Integer.parseInt(request.getParameter("id"));
                Optional<Eleve> e = eleveService.getEleveById(idEdit);

                if (e.isPresent()) {
                    request.setAttribute("eleve", e.get());
                    request.setAttribute("filieres", filiereService.findAllFilieres());
                    request.getRequestDispatcher("eleves/edit.jsp").forward(request, response);
                } else {
                    response.sendRedirect("eleves");
                }
                break;

            case "delete":
                int idDelete = Integer.parseInt(request.getParameter("id"));
                eleveService.deleteEleve(idDelete);
                response.sendRedirect("eleves");
                break;

            default: // LIST
                List<Eleve> liste = eleveService.getAllEleve();
                request.setAttribute("eleves", liste);
                request.getRequestDispatcher("eleves/list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String action = request.getParameter("action");

        if ("create".equals(action)) {
            Eleve eleve = new Eleve();
            eleve.setNom(request.getParameter("nom"));
            eleve.setPrenom(request.getParameter("prenom"));
            int filiereId = Integer.parseInt(request.getParameter("filiereId"));
            filiereService.findFiliereById(filiereId).ifPresent(eleve::setFiliere);

            eleveService.createEleve(eleve);
            response.sendRedirect("eleves");
        }

        if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));

            Optional<Eleve> opt = eleveService.getEleveById(id);
            if (opt.isPresent()) {
                Eleve eleve = opt.get();
                eleve.setNom(request.getParameter("nom"));
                eleve.setPrenom(request.getParameter("prenom"));

                int filiereId = Integer.parseInt(request.getParameter("filiereId"));
                filiereService.findFiliereById(filiereId).ifPresent(eleve::setFiliere);

                eleveService.updateEleve(eleve);
            }

            response.sendRedirect("eleves");
        }
    }
}
